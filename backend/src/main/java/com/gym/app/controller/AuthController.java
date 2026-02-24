package com.gym.app.controller;

import com.gym.app.dto.AuthDtos.*;
import com.gym.app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register") public TokenResponse register(@Valid @RequestBody RegisterRequest request) { return authService.register(request); }
    @PostMapping("/login") public TokenResponse login(@Valid @RequestBody LoginRequest request) { return authService.login(request); }
    @PostMapping("/refresh") public TokenResponse refresh(@Valid @RequestBody RefreshTokenRequest request) { return authService.refresh(request); }
    @PostMapping("/forgot-password") public void forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) { authService.forgotPassword(request); }
}
