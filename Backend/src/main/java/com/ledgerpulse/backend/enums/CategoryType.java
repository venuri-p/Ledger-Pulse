package com.ledgerpulse.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoryType {
    INCOME,
    EXPENSE;

    @JsonCreator
    public static CategoryType fromString(String value) {
        if (value == null) {
            return null;
        }
        return CategoryType.valueOf(value.toUpperCase());
    }
}
