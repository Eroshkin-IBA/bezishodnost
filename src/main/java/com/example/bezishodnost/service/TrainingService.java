package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Schedule;
import com.example.bezishodnost.model.Trainer;
import com.example.bezishodnost.model.Training;
import com.example.bezishodnost.model.TrainingTime;
import com.example.bezishodnost.pojo.FreeTrainings;
import com.example.bezishodnost.repo.TrainingRepo;
import com.example.bezishodnost.repo.TrainingTimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TrainingService {
    TrainingRepo trainingRepo;
    ScheduleService scheduleService;
    TrainingTimeRepo trainingTimeRepo;
public ArrayList<FreeTrainings> findFreeTraining(Trainer trainer){
    ArrayList<Schedule> scheduleArrayList = scheduleService.getScheduleByTrainer(trainer);
    ArrayList<TrainingTime> trainingTimes = new ArrayList<>();
    ArrayList<FreeTrainings> freeTrainings = new ArrayList<>();
    trainingTimeRepo.findAll().forEach(trainingTimes::add);
    for (Schedule schedule: scheduleArrayList) {
        ArrayList<TrainingTime> freeTimes = new ArrayList<>();
        for (TrainingTime trainingTime: trainingTimes ) {
            Optional<Training> training = trainingRepo.findByScheduleAndAndTrainingTime(schedule, trainingTime);
            if (training.isEmpty()) freeTimes.add(trainingTime);
        }
        FreeTrainings ft = new FreeTrainings();
        ft.setSchedule(schedule);
        ft.setTrainingTimes(freeTimes);
        freeTrainings.add(ft);
    }
    return freeTrainings;
}

}
