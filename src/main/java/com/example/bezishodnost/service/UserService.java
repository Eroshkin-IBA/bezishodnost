package com.example.bezishodnost.service;

import com.example.bezishodnost.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByLogin(String login);
    public Optional<User> findById(long id);
}