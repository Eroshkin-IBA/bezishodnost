package com.example.bezishodnost.controller;

import com.example.bezishodnost.model.ERole;
import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.Role;
import com.example.bezishodnost.model.User;
import com.example.bezishodnost.pojo.UserPojo;
import com.example.bezishodnost.repo.UserRepo;

import com.example.bezishodnost.service.PersonService;
import com.example.bezishodnost.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Controller
public class RegistrationController {
    private final UserRepo userRepo;
    private final UserService userService;
    private final PersonService personService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password,
                               @RequestParam("name") String name, @RequestParam("surName") String surName,
                               @RequestParam("phone") String phone, @RequestParam("date") String bDate, @RequestParam("file") MultipartFile file,
                               Model model) throws ParseException {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1l, ERole.ROLE_USER);

        roles.add(role);
        user.setRoles(roles);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

        Date date = formatter.parse(bDate);

        Person person = new Person();
        person.setName(name);
        person.setSurName(surName);
        person.setBDate(date);
        person.setPhone(phone);

        try {
            person.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        person.setUser(user);




        Optional<User> userFromDb = userRepo.findByLogin(login);

        if (userFromDb.isPresent()) {
            model.addAttribute("message", "Такое логин уже занят");
            return "registration";
        }


        userService.save(user);
        personService.save(person);


        return "redirect:/login";
    }

}