package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "ai_requests_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AIRequestLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User requestedBy;
    private String requestType;
    @Column(columnDefinition = "TEXT") private String prompt;
    @Column(columnDefinition = "TEXT") private String response;
    private LocalDateTime createdAt = LocalDateTime.now();
}
