package com.ledgerpulse.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfilePictureRequestDto {
    
    @NotBlank(message = "Profile picture URL is required")
    @URL(message = "Must be a valid URL")
    private String profilePicUrl;
}
