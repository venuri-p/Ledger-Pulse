package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    
    CategoryResponseDto createCategory(String userId, CategoryRequestDto requestDto);
    
    List<CategoryResponseDto> getUserCategories(String userId);
    
    CategoryResponseDto updateCategory(String userId, String categoryId, CategoryRequestDto requestDto);
    
    void deleteCategory(String userId, String categoryId);
}
