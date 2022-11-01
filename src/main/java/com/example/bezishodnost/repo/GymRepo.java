package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GymRepo extends JpaRepository<Gym, Integer> {
    public Optional<Gym> findById(Integer id);

}

