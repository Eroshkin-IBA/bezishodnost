package com.example.bezishodnost.controller;

import com.example.bezishodnost.model.User;
import com.example.bezishodnost.repo.UserRepo;

import com.example.bezishodnost.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class RegistrationController {
    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User userForm, Model model) {
        User user = new User();
        user.setPassword(userForm.getPassword());
        user.setLogin(userForm.getLogin());
        user.setActive(true);
        System.out.println(userForm.getPassword());
        Optional<User> userFromDb = userRepo.findByLogin(userForm.getLogin());

        if (userFromDb.isPresent()) {
            model.addAttribute("message", "Такое имя уже занято");
            return "registration";
        }


        userService.save(user);


        return "redirect:/login";
    }

}