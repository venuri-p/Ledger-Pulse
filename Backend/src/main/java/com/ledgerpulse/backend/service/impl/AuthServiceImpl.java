package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.LoginRequestDto;
import com.ledgerpulse.backend.dto.request.RegisterRequestDto;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.security.JwtUtil;
import com.ledgerpulse.backend.service.AuthService;
import com.ledgerpulse.backend.entity.PasswordResetToken;
import com.ledgerpulse.backend.repository.PasswordResetTokenRepository;
import com.ledgerpulse.backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;

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
    public String loginUser(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("password or email is incorrect"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("password or email is incorrect");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public void forgotPassword(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = UUID.randomUUID().toString();
            
            PasswordResetToken resetToken = PasswordResetToken.builder()
                    .user(user)
                    .token(token)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusMinutes(30))
                    .used(false)
                    .build();
            
            passwordResetTokenRepository.save(resetToken);
            
            String resetLink = "http://localhost:3000/reset-password?token=" + token;
            emailService.sendEmail(user.getEmail(), "Password Reset Request", 
                    "To reset your password, click the link below:\n" + resetLink);
        }
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid password reset token"));
        
        if (resetToken.isUsed()) {
            throw new IllegalArgumentException("Password reset token has already been used");
        }
        
        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Password reset token has expired");
        }
        
        User user = resetToken.getUser();
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);
    }
}
