package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.UpdatePasswordRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfilePictureRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfileRequestDto;
import com.ledgerpulse.backend.dto.response.UserProfileResponseDto;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileResponseDto getUserProfile(Long userId) {
        User user = getUserById(userId);
        return mapToUserProfileResponseDto(user);
    }

    @Override
    public UserProfileResponseDto updateUserProfile(Long userId, UpdateProfileRequestDto request) {
        User user = getUserById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        
        userRepository.save(user);
        return mapToUserProfileResponseDto(user);
    }

    @Override
    public UserProfileResponseDto updateProfilePicture(Long userId, UpdateProfilePictureRequestDto request) {
        User user = getUserById(userId);
        user.setProfilePicUrl(request.getProfilePicUrl());
        
        userRepository.save(user);
        return mapToUserProfileResponseDto(user);
    }

    @Override
    public void updatePassword(Long userId, UpdatePasswordRequestDto request) {
        User user = getUserById(userId);
        
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid current password");
        }
        
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    private UserProfileResponseDto mapToUserProfileResponseDto(User user) {
        return UserProfileResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .profilePicUrl(user.getProfilePicUrl())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
