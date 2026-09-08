package com.ledgerpulse.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not valid")
    private String email;
}
