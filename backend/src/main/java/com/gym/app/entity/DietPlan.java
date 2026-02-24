package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "diet_plan")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DietPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) private User member;
    @ManyToOne(optional = false) private User trainer;
    @Column(columnDefinition = "TEXT") private String content;
    private Integer targetCalories;
}
