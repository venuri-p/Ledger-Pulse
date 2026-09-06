package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.LoginRequestDto;
import com.ledgerpulse.backend.dto.request.RegisterRequestDto;

public interface AuthService {
    void registerUser(RegisterRequestDto requestDto);
    String loginUser(LoginRequestDto requestDto);
}
