package com.ledgerpulse.backend.repository;

import com.ledgerpulse.backend.entity.Category;
import com.ledgerpulse.backend.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByUserIdOrUserIsNull(String userId);
    Optional<Category> findByIdAndUserId(String id, String userId);
    boolean existsByNameAndTypeAndUserId(String name, CategoryType type, String userId);
}
