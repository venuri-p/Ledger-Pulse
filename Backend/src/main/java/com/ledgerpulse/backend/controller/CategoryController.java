package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;
import com.ledgerpulse.backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(
            @Valid @RequestBody CategoryRequestDto requestDto,
            Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(userId, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getUserCategories(Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(categoryService.getUserCategories(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable String id,
            @Valid @RequestBody CategoryRequestDto requestDto,
            Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(categoryService.updateCategory(userId, id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable String id,
            Authentication authentication) {
        String userId = authentication.getName();
        categoryService.deleteCategory(userId, id);
        return ResponseEntity.noContent().build();
    }
}
