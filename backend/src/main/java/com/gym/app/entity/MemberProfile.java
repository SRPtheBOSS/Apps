package com.gym.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name = "member_profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false) private User user;
    @ManyToOne private User trainer;
    private Double heightCm;
    private Double weightKg;
    private LocalDate membershipExpiry;
}
