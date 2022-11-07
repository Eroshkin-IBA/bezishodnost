package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepo extends JpaRepository<Training, Integer> {
    public List<Training> findAllBySchedule(Schedule schedule);
    public Optional<Training> findByScheduleAndAndTrainingTime(Schedule schedule, TrainingTime trainingTime);
}
