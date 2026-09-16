package com.ledgerpulse.backend.dto.response;

import com.ledgerpulse.backend.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private String id;
    private String name;
    private CategoryType type;
    private Boolean isGlobal;
    private LocalDateTime createdAt;
}
