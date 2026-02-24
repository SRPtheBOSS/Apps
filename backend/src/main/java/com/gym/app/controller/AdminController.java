package com.gym.app.controller;

import com.gym.app.dto.WorkoutPlanDto;
import com.gym.app.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/assign-trainer/{memberId}/{trainerId}")
    public void assignTrainer(@PathVariable Long memberId, @PathVariable Long trainerId) { adminService.assignTrainer(memberId, trainerId); }

    @PostMapping("/workout-plans")
    public void createWorkout(@Valid @RequestBody WorkoutPlanDto dto, Authentication auth) { adminService.createWorkoutPlan(dto, auth.getName()); }
}
