package com.gym.app.controller;

import com.gym.app.dto.AIPlanRequest;
import com.gym.app.service.AIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/ai") @RequiredArgsConstructor
public class AIController {
    private final AIService aiService;

    @PostMapping("/workout")
    public Map<String, String> workout(@Valid @RequestBody AIPlanRequest request, Authentication auth) { return Map.of("plan", aiService.generateWorkoutPlan(request, auth.getName())); }

    @PostMapping("/diet")
    public Map<String, String> diet(@Valid @RequestBody AIPlanRequest request, Authentication auth) { return Map.of("plan", aiService.generateDietPlan(request, auth.getName())); }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> body, Authentication auth) { return Map.of("answer", aiService.chat(body.getOrDefault("question", ""), auth.getName())); }
}
