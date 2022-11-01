package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.Trainer;
import com.example.bezishodnost.pojo.TrainerPojo;
import com.example.bezishodnost.repo.PersonRepo;
import com.example.bezishodnost.repo.TrainerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@AllArgsConstructor
@Service
public class TrainerService {

private final TrainerRepo trainerRepo;
private final PersonRepo personRepo;

    public ArrayList<Trainer> getAllTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        trainerRepo.findAll().forEach(trainers::add);
        return trainers;
    }

    public ArrayList<TrainerPojo> getTrainersByGym(Gym gym){
        ArrayList<TrainerPojo> trainerPojoList = new ArrayList<>();
        TrainerPojo trainerPojo=new TrainerPojo();
        Person person;
        for (Trainer trainer: trainerRepo.findAllByGym(gym)) {
            trainerPojo.setTrainerId(trainer.getTrainerId());
            trainerPojo.setExperience(trainer.getExperience());
            person = personRepo.findByUser(trainer.getUser()).get();
            trainerPojo.setName(person.getName());
            trainerPojo.setSurName(person.getSurName());
            trainerPojo.setSex(person.getSex());
            trainerPojo.setImage(person.getImage());
            trainerPojoList.add(trainerPojo);
        }
        return trainerPojoList;
    }

//    public void addTrainer(MultipartFile file, String city, String street, String house) {
//
//        Gym gym = new Gym();
//        gym.setCity(city);
//        gym.setStreet(street);
//        gym.setHouse(house);
//
//        try {
//            gym.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//
//
//        gymRepository.save(gym);
//    }

}
