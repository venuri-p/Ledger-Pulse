package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.UpdatePasswordRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfilePictureRequestDto;
import com.ledgerpulse.backend.dto.request.UpdateProfileRequestDto;
import com.ledgerpulse.backend.dto.response.UserProfileResponseDto;

public interface UserService {
    UserProfileResponseDto getUserProfile(String userId);
    UserProfileResponseDto updateUserProfile(String userId, UpdateProfileRequestDto requestDto);
    UserProfileResponseDto updateProfilePicture(String userId, UpdateProfilePictureRequestDto requestDto);
    void updatePassword(String userId, UpdatePasswordRequestDto requestDto);
}
