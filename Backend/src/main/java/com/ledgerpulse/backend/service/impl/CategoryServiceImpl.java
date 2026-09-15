package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.CategoryRequestDto;
import com.ledgerpulse.backend.dto.response.CategoryResponseDto;
import com.ledgerpulse.backend.entity.Category;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.enums.CategoryType;
import com.ledgerpulse.backend.repository.CategoryRepository;
import com.ledgerpulse.backend.repository.TransactionRepository;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public CategoryResponseDto createCategory(String userId, CategoryRequestDto requestDto) {
        if (categoryRepository.existsByNameAndTypeAndUserId(requestDto.getName(), requestDto.getType(), userId)) {
            throw new IllegalArgumentException("Category with this name and type already exists for the user");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = Category.builder()
                .name(requestDto.getName())
                .type(requestDto.getType())
                .user(user)
                .build();

        Category savedCategory = categoryRepository.save(category);
        return mapToDto(savedCategory);
    }

    @Override
    public CategoryResponseDto updateCategory(String userId, String categoryId, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or does not belong to the user"));

        if (!category.getName().equals(requestDto.getName()) || category.getType() != requestDto.getType()) {
             if (categoryRepository.existsByNameAndTypeAndUserId(requestDto.getName(), requestDto.getType(), userId)) {
                 throw new IllegalArgumentException("Category with this name and type already exists for the user");
             }
        }

        category.setName(requestDto.getName());
        category.setType(requestDto.getType());

        Category updatedCategory = categoryRepository.save(category);
        return mapToDto(updatedCategory);
    }

    @Override
    public List<CategoryResponseDto> getCategories(String userId, CategoryType type) {
        List<Category> categories = categoryRepository.findByUserIdOrUserIsNull(userId);
        
        if (type != null) {
            categories = categories.stream()
                    .filter(c -> c.getType() == type)
                    .collect(Collectors.toList());
        }

        return categories.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String userId, String categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or does not belong to the user"));

        if (transactionRepository.existsByCategoryId(categoryId)) {
            throw new IllegalStateException("Cannot delete category as it is currently associated with one or more transactions");
        }

        categoryRepository.delete(category);
    }

    private CategoryResponseDto mapToDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .isGlobal(category.getUser() == null)
                .build();
    }
}
