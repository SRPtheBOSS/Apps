package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "trainer_profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainerProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false) private User user;
    private String specialization;
    private Integer experienceYears;
}
