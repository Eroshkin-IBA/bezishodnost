package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepo extends JpaRepository<Gym, Integer> {
}

