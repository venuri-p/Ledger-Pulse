package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;
import com.ledgerpulse.backend.enums.CategoryType;
import com.ledgerpulse.backend.security.CustomUserDetails;
import com.ledgerpulse.backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto requestDto) {
        String userId = getCurrentUserId();
        return new ResponseEntity<>(categoryService.createCategory(userId, requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable String id, 
            @Valid @RequestBody CategoryRequestDto requestDto) {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(categoryService.updateCategory(userId, id, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories(
            @RequestParam(required = false) CategoryType type) {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(categoryService.getCategories(userId, type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        String userId = getCurrentUserId();
        categoryService.deleteCategory(userId, id);
        return ResponseEntity.noContent().build();
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUser().getId();
        }
        throw new RuntimeException("User not authenticated");
    }
}
