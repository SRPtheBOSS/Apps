package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name = "progress_tracking")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProgressTracking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User member;
    private LocalDate logDate;
    private Double weightKg;
    private Double bodyFatPercent;
    private Double bmi;
    private String notes;
}
