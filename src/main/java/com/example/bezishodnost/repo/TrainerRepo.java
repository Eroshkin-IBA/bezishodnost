package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Integer> {
    public Optional<Trainer> findById(Integer id);
    public List<Trainer> findAllByGym(Gym id);

}
