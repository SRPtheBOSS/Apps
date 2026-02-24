package com.gym.app.service.impl;

import com.gym.app.dto.WorkoutPlanDto;
import com.gym.app.entity.WorkoutPlan;
import com.gym.app.exception.AppException;
import com.gym.app.repository.MemberProfileRepository;
import com.gym.app.repository.UserRepository;
import com.gym.app.repository.WorkoutPlanRepository;
import com.gym.app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final MemberProfileRepository memberProfileRepository;
    private final UserRepository userRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public void assignTrainer(Long memberId, Long trainerId) {
        var memberProfile = memberProfileRepository.findById(memberId).orElseThrow(() -> new AppException("Member profile not found"));
        var trainer = userRepository.findById(trainerId).orElseThrow(() -> new AppException("Trainer not found"));
        memberProfile.setTrainer(trainer);
        memberProfileRepository.save(memberProfile);
    }

    @Override
    public void createWorkoutPlan(WorkoutPlanDto dto, String trainerEmail) {
        var member = userRepository.findById(dto.getMemberId()).orElseThrow(() -> new AppException("Member not found"));
        var trainer = userRepository.findByEmail(trainerEmail).orElseThrow(() -> new AppException("Trainer not found"));
        workoutPlanRepository.save(WorkoutPlan.builder().member(member).trainer(trainer).content(dto.getContent()).build());
    }
}
