package com.example.bezishodnost.pojo;

import com.example.bezishodnost.model.Schedule;
import com.example.bezishodnost.model.TrainingTime;
import lombok.Data;

import java.util.ArrayList;
@Data
public class FreeTrainings {

    private Schedule schedule;
    private ArrayList<TrainingTime> trainingTimes;
}
