package com.example.bezishodnost.controller;

import com.example.bezishodnost.model.*;
import com.example.bezishodnost.pojo.TrainerPojo;
import com.example.bezishodnost.repo.TrainerRepo;
import com.example.bezishodnost.repo.UserRepo;
import com.example.bezishodnost.service.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Controller
public class MainController {
    private final UserRepo userRepo;
    private final UserService userService;
    private final GymService gymService;
    private final TrainerRepo trainerRepo;
    private final PersonService personService;
    private final TrainerService trainerService;
    private final ScheduleService scheduleService;
    private final TrainingService trainingService;


    @GetMapping("/")
    public String getGyms(Model model) {
        ArrayList<Gym> gyms = gymService.getAllGyms();
        model.addAttribute("gyms", gyms);
        return "main";
    }

    @GetMapping("/gym/{id}")
    public String getGymInfo(@PathVariable("id") Integer id, Model model) {
        Gym gym = gymService.getById(id);

        ArrayList<TrainerPojo> trainers = trainerService.getTrainersByGym(gym);
        model.addAttribute("gym", gym);
        model.addAttribute("hi", "lox");
        model.addAttribute("trainers", trainers);
        return "gym";
    }

    @PostMapping("/addGym")
    public String addGym(@RequestParam("file") MultipartFile file, @RequestParam("city") String city,
                         @RequestParam("street") String street, @RequestParam("house") String house) {
        gymService.addGym(file, city, street, house);
        return "redirect:/";
    }


    @GetMapping("/gym/{id}/schedule")
    public String schedule(@PathVariable("id") Integer id, Model model) {
        Gym gym = gymService.getById(id);
        ArrayList<TrainerPojo> trainers = trainerService.getTrainersByGym(gym);
        model.addAttribute("gym", gym);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("trainers", trainers);
        model.addAttribute("schedules", scheduleService.getScheduleByGym(gym, new Date()));
        return "schedule";
    }

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam("trainerId") int id, @RequestParam("date")String sDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

        Date date = formatter.parse(sDate);

        Trainer trainerFromDB = trainerService.getTrainerById(id);
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setTrainer(trainerFromDB);
        schedule.setGym(trainerFromDB.getGym());
        scheduleService.add(schedule);

        return "redirect:/gym/" + trainerFromDB.getGym().getId()+"/schedule";
    }


    @PostMapping("/addTrainer")
    public String addTrainer(@RequestParam("file") MultipartFile file, @RequestParam("login") String login,
                             @RequestParam("password") String password, @RequestParam("name") String name,
                             @RequestParam("surname") String surname, @RequestParam("experience") int experience,
                             @RequestParam("sex") int sex) {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        Role role = new Role(2l, ERole.ROLE_TRAINER);
        roles.add(role);
        user.setRoles(roles);

        userService.save(user);

        Person person = new Person();
        person.setName(name);
        person.setSurName(surname);
        person.setSex(sex);
        person.setUser(user);
        try {
            person.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        personService.save(person);

        Trainer trainer = new Trainer();
        trainer.setExperience(experience);
        trainer.setUser(user);


        trainerRepo.save(trainer);
        return "redirect:/";
    }

    @GetMapping("/gym/{id}/trainer/{trainerId}")
    public String training(@PathVariable("id") Integer id,@PathVariable("trainerId") Integer trainerId, Model model){
        Trainer trainer = trainerService.getTrainerById(trainerId);
        model.addAttribute("trainings", trainingService.findFreeTraining(trainer));

        return "trainer";
    }

}
