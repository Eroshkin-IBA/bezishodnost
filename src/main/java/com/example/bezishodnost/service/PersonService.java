package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.User;
import com.example.bezishodnost.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepo personRepo;

    public void save(Person person) {
        personRepo.save(person);
    }


    public Person findByUser(User user) {
        return personRepo.findByUser(user).get();
    }
}
