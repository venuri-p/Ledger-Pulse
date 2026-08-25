package com.ledgerpulse.backend.repository;

import com.ledgerpulse.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccountId(String accountId);
    List<Transaction> findByCategoryId(String categoryId);
}
