package com.ledgerpulse.backend.dto.response;

import com.ledgerpulse.backend.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private String id;
    private String name;
    private CategoryType type;
    private boolean isGlobal;
}
