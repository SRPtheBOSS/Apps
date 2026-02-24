package com.gym.app.repository;

import com.gym.app.entity.ProgressTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTracking, Long> {}
