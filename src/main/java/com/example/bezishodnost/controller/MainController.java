package com.example.bezishodnost.controller;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.repo.UserRepo;
import com.example.bezishodnost.service.GymService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@AllArgsConstructor
@Controller
public class MainController {
    private final UserRepo userRepo;
    private final GymService gymService;


    @GetMapping("/")
    public String getGyms(Model model) {
        ArrayList<Gym> gyms = gymService.getAllGyms();
        model.addAttribute("gyms", gyms);
        return "main";
    }

    @GetMapping("/gym/{id}")
    public String getGymInfo(@PathVariable("id") Integer id, Model model) {
        Gym gym = gymService.getById(id);
        model.addAttribute("gym", gym);
        return "gym";
    }

    @PostMapping("/addGym")
    public String addGym(@RequestParam("file") MultipartFile file, @RequestParam("city") String city,
                         @RequestParam("street") String street, @RequestParam("house") String house) {
        gymService.addGym(file, city, street, house);
        return "redirect:/";
    }


    @GetMapping("/aaa")
    public String aaa(Model model) {

        model.addAttribute("users", userRepo.findAll());
        return "aaa";
    }

}
