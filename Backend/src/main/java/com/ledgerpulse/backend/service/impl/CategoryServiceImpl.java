package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;
import com.ledgerpulse.backend.entity.Category;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.exception.ResourceNotFoundException;
import com.ledgerpulse.backend.repository.CategoryRepository;
import com.ledgerpulse.backend.repository.TransactionRepository;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(String userId, CategoryRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (categoryRepository.existsByNameAndUserId(requestDto.getName(), userId)) {
            throw new IllegalArgumentException("Category with this name already exists for the user");
        }

        Category category = Category.builder()
                .name(requestDto.getName())
                .type(requestDto.getType())
                .user(user)
                .build();

        Category savedCategory = categoryRepository.save(category);
        return mapToResponseDto(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getUserCategories(String userId) {
        return categoryRepository.findCategoriesAvailableToUser(userId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(String userId, String categoryId, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (category.getUser() == null || !category.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You do not have permission to update this category");
        }

        if (!category.getName().equals(requestDto.getName()) && 
            categoryRepository.existsByNameAndUserId(requestDto.getName(), userId)) {
            throw new IllegalArgumentException("Category with this name already exists for the user");
        }

        category.setName(requestDto.getName());
        category.setType(requestDto.getType());

        Category updatedCategory = categoryRepository.save(category);
        return mapToResponseDto(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(String userId, String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (category.getUser() == null || !category.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You do not have permission to delete this category");
        }

        if (transactionRepository.existsByCategoryId(categoryId)) {
            throw new IllegalArgumentException("Cannot delete category because it is associated with transactions");
        }

        categoryRepository.delete(category);
    }

    private CategoryResponseDto mapToResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .isGlobal(category.getUser() == null)
                .createdAt(category.getCreatedAt())
                .build();
    }
}
