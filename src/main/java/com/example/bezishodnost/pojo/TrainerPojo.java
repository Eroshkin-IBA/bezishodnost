package com.example.bezishodnost.pojo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerPojo {
    private int trainerId;
    private String name;
    private String surName;

    private int sex;
    private int experience;
    private String image;
}
