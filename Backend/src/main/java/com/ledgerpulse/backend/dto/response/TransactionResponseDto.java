package com.ledgerpulse.backend.dto.response;

import com.ledgerpulse.backend.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private String id;
    private BigDecimal amount;
    private CategoryType type;
    private LocalDateTime date;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String accountId;
    private String accountName;
    
    private String categoryId;
    private String categoryName;
    private CategoryType categoryType;
}
