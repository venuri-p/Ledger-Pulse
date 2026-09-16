package com.ledgerpulse.backend.repository;

import com.ledgerpulse.backend.entity.Category;
import com.ledgerpulse.backend.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findByUserIsNullAndType(CategoryType type);

    List<Category> findByUserIdAndType(String userId, CategoryType type);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId OR c.user IS NULL")
    List<Category> findByUserIdOrUserIsNull(@Param("userId") String userId);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId OR c.user IS NULL ORDER BY c.createdAt DESC")
    List<Category> findCategoriesAvailableToUser(@Param("userId") String userId);

    boolean existsByNameAndUserId(String name, String userId);
}
