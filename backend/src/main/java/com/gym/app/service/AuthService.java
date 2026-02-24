package com.gym.app.service;

import com.gym.app.dto.AuthDtos.*;

public interface AuthService {
    TokenResponse register(RegisterRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse refresh(RefreshTokenRequest request);
    void forgotPassword(ForgotPasswordRequest request);
}
