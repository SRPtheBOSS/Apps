package com.gym.app.service;

import com.gym.app.dto.AIPlanRequest;

public interface AIService {
    String generateWorkoutPlan(AIPlanRequest request, String requesterEmail);
    String generateDietPlan(AIPlanRequest request, String requesterEmail);
    String chat(String question, String requesterEmail);
}
