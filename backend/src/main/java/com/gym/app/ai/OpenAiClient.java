package com.gym.app.ai;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component @RequiredArgsConstructor
public class OpenAiClient {
    private static final Logger log = LoggerFactory.getLogger(OpenAiClient.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${app.ai.base-url:https://api.openai.com/v1/chat/completions}") private String baseUrl;
    @Value("${app.ai.api-key:}") private String apiKey;
    @Value("${app.ai.model:gpt-4o-mini}") private String model;

    public String chat(String prompt) {
        if (apiKey == null || apiKey.isBlank()) return "AI key not configured; mock response for prompt: " + prompt;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> payload = Map.of("model", model, "messages", List.of(Map.of("role", "user", "content", prompt)));
        ResponseEntity<Map> response = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(payload, headers), Map.class);
        log.info("AI response status: {}", response.getStatusCode());
        var choices = (List<Map<String, Object>>) response.getBody().get("choices");
        var msg = (Map<String, Object>) choices.get(0).get("message");
        return String.valueOf(msg.get("content"));
    }
}
