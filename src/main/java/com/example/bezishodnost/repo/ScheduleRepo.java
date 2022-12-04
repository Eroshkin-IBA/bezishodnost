package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.model.Role;
import com.example.bezishodnost.model.Schedule;
import com.example.bezishodnost.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    Optional <Schedule> findById(int id);
    public List<Schedule> findAllByGymAndDateIsGreaterThanOrderByDate(Gym id, Date date);
    public List<Schedule> findAllByTrainerAndDateIsGreaterThanOrderByDate(Trainer id, Date date);
}
