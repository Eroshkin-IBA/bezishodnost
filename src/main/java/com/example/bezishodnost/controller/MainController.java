package com.example.bezishodnost.controller;

import com.example.bezishodnost.model.*;
import com.example.bezishodnost.pojo.ForTrainerTrainings;
import com.example.bezishodnost.pojo.TrainerPojo;
import com.example.bezishodnost.repo.TrainerRepo;
import com.example.bezishodnost.repo.TrainingRepo;
import com.example.bezishodnost.repo.TrainingTimeRepo;
import com.example.bezishodnost.repo.UserRepo;
import com.example.bezishodnost.service.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private final TrainingTimeRepo trainingTimeRepo;

    @GetMapping("/profile/{id}")
    public String getUserInfo(@PathVariable("id") Integer id, Model model) {

        if (userService.findById((long) id).isEmpty()) {
            return "notFound";
        }
        User user = userService.findById((long) id).get();
        Optional<Trainer> trainerOptional = trainerService.findByUser(user);
        if (trainerOptional.isPresent()) {
            System.out.println("HELLLLLLO");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date date = cal.getTime();
            ArrayList<Schedule> scheduleArrayList = scheduleService.getScheduleByTrainer(trainerOptional.get(), date);

            ArrayList<ForTrainerTrainings> ftt = trainingService.forTrainer(scheduleArrayList);

            model.addAttribute("ftt",trainingService.forTrainer(scheduleArrayList));

        }
        model.addAttribute("person", personService.findByUser(user));
        model.addAttribute("trainings", trainingService.findByUser(user));
        model.addAttribute("id", user.getUserId());

        return "profile";
    }

    @GetMapping("/gyms")
    public String getGyms(Model model) {
        ArrayList<Gym> gyms = gymService.getAllGyms();
        model.addAttribute("gyms", gyms);
        return "main";
    }

    @GetMapping("/")
    public String index(Model model) {

        return "index";
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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        ArrayList<TrainerPojo> trainers = trainerService.getTrainersByGym(gym);
        model.addAttribute("gym", gym);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("trainers", trainers);
        model.addAttribute("schedules", scheduleService.getScheduleByGym(gym, date));
        return "schedule";
    }

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam("trainerId") int id, @RequestParam("date") String sDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

        Date date = formatter.parse(sDate);

        Trainer trainerFromDB = trainerService.getTrainerById(id);
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setTrainer(trainerFromDB);
        schedule.setGym(trainerFromDB.getGym());
        scheduleService.add(schedule);

        return "redirect:/gym/" + trainerFromDB.getGym().getId() + "/schedule";
    }

    @PostMapping("delSchedule")
    public String delSchedule(@RequestParam("scheduleId") int sId, @RequestParam("gymId") int gId) {
        scheduleService.delSchedule(sId);
        Gym gym = gymService.getById(gId);
        return "redirect:/gym/" + gym.getId() + "/schedule";
    }


    @PostMapping("/addTrainer")
    public String addTrainer(@RequestParam("file") MultipartFile file,@RequestParam("tel") String tel, @RequestParam("login") String login,
                             @RequestParam("password") String password, @RequestParam("name") String name,
                             @RequestParam("surname") String surname, @RequestParam("experience") int experience,
                             @RequestParam("sex") int sex,@RequestParam("date") String sDate, @RequestParam("gymId") int gymId) throws ParseException {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        Role role = new Role(2l, ERole.ROLE_TRAINER);
        roles.add(role);
        user.setRoles(roles);

        userService.save(user);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

        Date date = formatter.parse(sDate);

        Person person = new Person();
        person.setName(name);
        person.setSurName(surname);
        person.setSex(sex);
        person.setUser(user);
        person.setPhone(tel);
        try {
            person.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        personService.save(person);

        Trainer trainer = new Trainer();
        trainer.setExperience(experience);
        trainer.setUser(user);
        trainer.setGym(gymService.getById(gymId));


        trainerRepo.save(trainer);
        return "redirect:/";
    }

    @GetMapping("/gym/{id}/trainer/{trainerId}")
    public String training(@PathVariable("id") Integer id, @PathVariable("trainerId") Integer trainerId, Model model) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();

model.addAttribute("trainer", personService.findByUser(trainer.getUser()));
        model.addAttribute("trainings", trainingService.findFreeTraining(trainer, date));

        return "trainer";
    }

    @PostMapping("trainingReg")
    public String reg(@RequestParam("userId") int userId,
                      @RequestParam("timeId") int timeId,
                      @RequestParam("scheduleId") int scheduleId) {
        User user = userService.findById((long) userId).get();
        Schedule schedule = scheduleService.findById(scheduleId);
        TrainingTime trainingTime = trainingTimeRepo.findById(timeId).get();
        Training training = new Training();
        training.setTrainingTime(trainingTime);
        training.setSchedule(schedule);
        training.setUser(user);
        trainingService.save(training);
        return "redirect:/profile/" + userId;
    }

    @PostMapping("/training/{id}")
    public String del(@PathVariable("id") int id, @RequestParam("userId") int uId){
        trainingService.delete(trainingService.findById(id));

        return "redirect:/profile/" + uId;
    }

    @PostMapping("/delTrainer/{id}")
    public String delT(@PathVariable("id") int id, @RequestParam("gymId") int uId){

        trainerRepo.delete(trainerRepo.findById(id).get());

        return "redirect:/gym/" + uId;
    }

}
