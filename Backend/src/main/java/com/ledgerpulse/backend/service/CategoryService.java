package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;
import com.ledgerpulse.backend.enums.CategoryType;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(String userId, CategoryRequestDto requestDto);
    CategoryResponseDto updateCategory(String userId, String categoryId, CategoryRequestDto requestDto);
    List<CategoryResponseDto> getCategories(String userId, CategoryType type);
    void deleteCategory(String userId, String categoryId);
}
