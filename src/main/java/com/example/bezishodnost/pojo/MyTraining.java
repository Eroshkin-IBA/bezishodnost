package com.example.bezishodnost.pojo;

import com.example.bezishodnost.model.*;
import lombok.Data;

import java.util.ArrayList;

@Data
public class MyTraining {
    private int id;
    private Schedule schedule;
    private TrainingTime trainingTime;
    private Person trainer;
    private Gym gym;
    private User u;
}
