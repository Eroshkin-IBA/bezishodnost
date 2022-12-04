package com.example.bezishodnost.pojo;

import com.example.bezishodnost.model.*;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ForTrainerTrainings {
    private Schedule schedule;
    private ArrayList<MyTraining> myTrainings;

}
