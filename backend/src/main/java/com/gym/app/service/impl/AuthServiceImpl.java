package com.gym.app.service.impl;

import com.gym.app.dto.AuthDtos.*;
import com.gym.app.entity.User;
import com.gym.app.exception.AppException;
import com.gym.app.repository.RoleRepository;
import com.gym.app.repository.UserRepository;
import com.gym.app.security.JwtService;
import com.gym.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override @Transactional
    public TokenResponse register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) throw new AppException("Email already exists");
        var roles = req.getRoles().stream().map(r -> roleRepository.findByName(r).orElseThrow(() -> new AppException("Role missing: " + r))).collect(java.util.stream.Collectors.toSet());
        User user = User.builder().email(req.getEmail()).password(passwordEncoder.encode(req.getPassword())).fullName(req.getFullName()).roles(roles).enabled(true).build();
        userRepository.save(user);
        return tokens(user.getEmail());
    }

    @Override
    public TokenResponse login(LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        return tokens(req.getEmail());
    }

    @Override
    public TokenResponse refresh(RefreshTokenRequest request) {
        if (!jwtService.isValid(request.getRefreshToken())) throw new AppException("Invalid refresh token");
        return tokens(jwtService.extractUsername(request.getRefreshToken()));
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) { if (userRepository.findByEmail(request.getEmail()).isEmpty()) throw new AppException("Email not found"); }

    private TokenResponse tokens(String email) {
        String access = jwtService.generateAccessToken(email, Map.of("email", email));
        String refresh = jwtService.generateRefreshToken(email);
        return new TokenResponse(access, refresh, "Bearer");
    }
}
