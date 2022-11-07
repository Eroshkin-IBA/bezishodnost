package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Training;
import com.example.bezishodnost.model.TrainingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingTimeRepo extends JpaRepository<TrainingTime, Integer> {
    public List<TrainingTime> findAll();
}
