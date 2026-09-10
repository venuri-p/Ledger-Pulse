package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.UpdatePasswordRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfilePictureRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfileRequestDto;
import com.ledgerpulse.backend.dto.response.UserProfileResponseDto;

public interface UserService {
    UserProfileResponseDto getUserProfile(Long userId);
    UserProfileResponseDto updateUserProfile(Long userId, UpdateProfileRequestDto request);
    UserProfileResponseDto updateProfilePicture(Long userId, UpdateProfilePictureRequestDto request);
    void updatePassword(Long userId, UpdatePasswordRequestDto request);
}
