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
    public String registration(UserPojo userPojo, Model model) {
        User user = new User();
        user.setPassword(userPojo.getPassword());
        user.setLogin(userPojo.getLogin());
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1l, ERole.ROLE_USER);

        roles.add(role);
        user.setRoles(roles);

        Person person = new Person();
        person.setName(userPojo.getName());
        person.setSurName(userPojo.getSurName());

        person.setSex(userPojo.getSex());
        person.setUser(user);



        System.out.println(userPojo.getPassword());
        Optional<User> userFromDb = userRepo.findByLogin(userPojo.getLogin());

        if (userFromDb.isPresent()) {
            model.addAttribute("message", "Такое логин уже занят");
            return "registration";
        }


        userService.save(user);
        personService.save(person);


        return "redirect:/login";
    }

}