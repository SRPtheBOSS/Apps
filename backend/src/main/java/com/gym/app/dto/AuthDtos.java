package com.gym.app.dto;

import com.gym.app.entity.enums.RoleType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

public class AuthDtos {
    @Data public static class LoginRequest { @Email @NotBlank private String email; @NotBlank private String password; }
    @Data public static class RegisterRequest { @Email @NotBlank private String email; @NotBlank private String password; @NotBlank private String fullName; @NotNull private Set<RoleType> roles; }
    @Data @AllArgsConstructor public static class TokenResponse { private String accessToken; private String refreshToken; private String tokenType; }
    @Data public static class RefreshTokenRequest { @NotBlank private String refreshToken; }
    @Data public static class ForgotPasswordRequest { @Email @NotBlank private String email; }
}
