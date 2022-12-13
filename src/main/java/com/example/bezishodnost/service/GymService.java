package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.repo.GymRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GymService {
    private final GymRepo gymRepository;

    public ArrayList<Gym> getAllGyms() {
        ArrayList<Gym> gyms = new ArrayList<>();
        gymRepository.findAll().forEach(gyms::add);
        return gyms;
    }


    public void addGym(MultipartFile file, String city, String street, String house) {

        Gym gym = new Gym();
        gym.setCity(city);
        gym.setStreet(street);
        gym.setHouse(house);

        try {
            gym.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }


        gymRepository.save(gym);
    }

    public Gym getById(Integer id){
        Optional<Gym> gymFromDb = gymRepository.findById(id);


        return gymFromDb.get();
    }

    public void delGym(int id){
        gymRepository.delete(gymRepository.findById(id).get());

    }

}
