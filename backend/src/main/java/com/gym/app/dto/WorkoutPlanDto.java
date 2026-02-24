package com.gym.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkoutPlanDto {
    @NotNull private Long memberId;
    @NotBlank private String content;
}
