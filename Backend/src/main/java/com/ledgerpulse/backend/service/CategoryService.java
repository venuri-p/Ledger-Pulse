package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    
    CategoryResponseDto createCategory(String email, CategoryRequestDto requestDto);
    
    List<CategoryResponseDto> getUserCategories(String email);
    
    CategoryResponseDto updateCategory(String email, String categoryId, CategoryRequestDto requestDto);
    
    void deleteCategory(String email, String categoryId);
}
