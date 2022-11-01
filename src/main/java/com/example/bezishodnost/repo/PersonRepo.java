package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {
    public Optional<Person> findByUser(User user);
}
