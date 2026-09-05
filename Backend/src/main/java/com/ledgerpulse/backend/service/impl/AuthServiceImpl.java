package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.LoginRequestDto;
import com.ledgerpulse.backend.dto.request.RegisterRequestDto;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .passwordHash(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public void loginUser(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("password or email is incorrect"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("password or email is incorrect");
        }
    }
}
