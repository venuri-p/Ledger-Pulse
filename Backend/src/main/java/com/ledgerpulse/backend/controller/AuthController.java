package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.LoginRequestDto;
import com.ledgerpulse.backend.dto.request.RegisterRequestDto;
import com.ledgerpulse.backend.dto.response.LoginResponseDto;
import com.ledgerpulse.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        authService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        String token = authService.loginUser(requestDto);
        return ResponseEntity.ok(LoginResponseDto.builder().token(token).build());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody com.ledgerpulse.backend.dto.request.ForgotPasswordRequestDto requestDto) {
        authService.forgotPassword(requestDto.getEmail());
        return ResponseEntity.ok("If an account exists for this email, a password reset link has been sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody com.ledgerpulse.backend.dto.request.ResetPasswordRequestDto requestDto) {
        authService.resetPassword(requestDto.getToken(), requestDto.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }
}
