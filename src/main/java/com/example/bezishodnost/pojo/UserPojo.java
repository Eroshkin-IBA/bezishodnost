package com.example.bezishodnost.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UserPojo {
    private String login;
    private String password;
    private String name;
    private String surName;

    private int sex;
}
