package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.User;
import com.example.bezishodnost.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

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

    public void update(String phone, long id){
        personRepo.updatePhone(phone, id);
    }
    public void updatePhoto(MultipartFile file, long id){
        try {
            String img = Base64.getEncoder().encodeToString(file.getBytes());
            personRepo.updatePhoto(img, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
