package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepo extends JpaRepository<Training, Integer> {
    public List<Training> findAllByScheduleOrderByTrainingTime(Schedule schedule);
    public List<Training> findAllByUser(User user);

    public Optional<Training> findByScheduleAndAndTrainingTime(Schedule schedule, TrainingTime trainingTime);
    public Optional<Training> findByTrainingId(int id);
}
