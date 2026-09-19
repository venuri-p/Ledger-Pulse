package com.ledgerpulse.backend.repository.specification;

import com.ledgerpulse.backend.entity.Transaction;
import com.ledgerpulse.backend.enums.CategoryType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransactionSpecification {

    public static Specification<Transaction> belongsToUser(String userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("account").get("user").get("id"),
                    userId
            );
        };
    }

    public static Specification<Transaction> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("notes")),
                    "%" + keyword.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Transaction> hasType(CategoryType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("type"),
                    type
            );
        };
    }

    public static Specification<Transaction> hasCategory(String categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null || categoryId.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("category").get("id"),
                    categoryId
            );
        };
    }

    public static Specification<Transaction> dateAfterOrEqual(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("date"),
                    startDate
            );
        };
    }

    public static Specification<Transaction> dateBeforeOrEqual(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (endDate == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get("date"),
                    endDate
            );
        };
    }
}
