package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
