package com.gym.app.service.impl;

import com.gym.app.ai.OpenAiClient;
import com.gym.app.dto.AIPlanRequest;
import com.gym.app.entity.AIRequestLog;
import com.gym.app.exception.AppException;
import com.gym.app.repository.AIRequestLogRepository;
import com.gym.app.repository.UserRepository;
import com.gym.app.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AIServiceImpl implements AIService {
    private final OpenAiClient aiClient;
    private final AIRequestLogRepository logRepository;
    private final UserRepository userRepository;

    @Override
    public String generateWorkoutPlan(AIPlanRequest r, String email) {
        return doAsk(buildPrompt("workout", r), email, "WORKOUT");
    }

    @Override
    public String generateDietPlan(AIPlanRequest r, String email) {
        return doAsk(buildPrompt("diet", r), email, "DIET");
    }

    @Override
    public String chat(String question, String email) {
        return doAsk("Act as a gym coach chatbot and answer: " + question, email, "CHATBOT");
    }

    private String doAsk(String prompt, String email, String type) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new AppException("User not found"));
        var result = aiClient.chat(prompt);
        logRepository.save(AIRequestLog.builder().requestedBy(user).requestType(type).prompt(prompt).response(result).build());
        return result;
    }

    private String buildPrompt(String mode, AIPlanRequest r) {
        return "Generate a " + mode + " plan for age=" + r.getAge() + ", height=" + r.getHeightCm() + "cm, weight=" + r.getWeightKg() + "kg, goal=" + r.getGoal() + ", experience=" + r.getExperienceLevel() + ", injuries=" + r.getInjuries();
    }
}
