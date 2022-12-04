package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findByLogin(String userName);
    public Optional<User> findByUserId(long Id);
}
