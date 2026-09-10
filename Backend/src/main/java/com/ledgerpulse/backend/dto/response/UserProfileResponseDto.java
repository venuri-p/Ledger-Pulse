package com.ledgerpulse.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
