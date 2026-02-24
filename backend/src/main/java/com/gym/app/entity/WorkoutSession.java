package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name = "workout_session")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkoutSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User member;
    @ManyToOne(optional = false) private WorkoutPlan workoutPlan;
    private LocalDate sessionDate;
    private boolean completed;
    private String remarks;
}
