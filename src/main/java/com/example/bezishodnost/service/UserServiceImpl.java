package com.example.bezishodnost.service;

import com.example.bezishodnost.model.ERole;
import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.Role;
import com.example.bezishodnost.model.User;
import com.example.bezishodnost.repo.RoleRepo;
import com.example.bezishodnost.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Bean
    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    };

    private UserRepo userRepo;






    @Override
    public void save(User user) {


        user.setPassword(encoder().encode(user.getPassword()));


        userRepo.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepo.findByLogin(login);
    }
    @Override
    public Optional<User> findById(long id) {
        return userRepo.findByUserId(id);
    }
}