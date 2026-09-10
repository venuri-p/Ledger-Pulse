package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.UpdatePasswordRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfilePictureRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfileRequestDto;
import com.ledgerpulse.backend.dto.response.UserProfileResponseDto;
import com.ledgerpulse.backend.security.CustomUserDetails;
import com.ledgerpulse.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfileResponseDto> getProfile() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PutMapping
    public ResponseEntity<UserProfileResponseDto> updateProfile(@Valid @RequestBody UpdateProfileRequestDto request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(userService.updateUserProfile(userId, request));
    }

    @PutMapping("/profile-picture")
    public ResponseEntity<UserProfileResponseDto> updateProfilePicture(@Valid @RequestBody UpdateProfilePictureRequestDto request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(userService.updateProfilePicture(userId, request));
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequestDto request) {
        Long userId = getCurrentUserId();
        userService.updatePassword(userId, request);
        return ResponseEntity.ok("Password updated successfully");
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUser().getId();
        }
        throw new RuntimeException("User not authenticated");
    }
}
