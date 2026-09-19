package com.ledgerpulse.backend.repository;

import com.ledgerpulse.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository 
        extends JpaRepository<Transaction, String>,
            JpaSpecificationExecutor<Transaction> {

            List<Transaction> findByAccountId(String accountId);
            List<Transaction> findByCategoryId(String categoryId);
            boolean existsByCategoryId(String categoryId);

            @Query("SELECT t FROM Transaction t WHERE t.account.user.id = :userId ORDER BY t.date DESC")
            List<Transaction> findAllByUserId(@Param("userId") String userId);

            Optional<Transaction> findByIdAndAccountUserId(String id, String userId);
}
