package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name = "workout_plan")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkoutPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User member;
    @ManyToOne(optional = false) private User trainer;
    @Column(columnDefinition = "TEXT") private String content;
    private LocalDate validFrom;
    private LocalDate validTo;
}
