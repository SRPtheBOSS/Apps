package com.gym.app.dto;

import com.gym.app.entity.enums.GoalType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AIPlanRequest {
    @Min(12) private int age;
    @Positive private double heightCm;
    @Positive private double weightKg;
    @NotNull private GoalType goal;
    @NotBlank private String experienceLevel;
    private String injuries;
}
