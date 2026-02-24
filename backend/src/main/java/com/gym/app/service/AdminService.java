package com.gym.app.service;

import com.gym.app.dto.WorkoutPlanDto;

public interface AdminService {
    void assignTrainer(Long memberId, Long trainerId);
    void createWorkoutPlan(WorkoutPlanDto dto, String trainerEmail);
}
