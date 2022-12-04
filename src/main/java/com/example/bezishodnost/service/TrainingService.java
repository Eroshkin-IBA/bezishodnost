package com.example.bezishodnost.service;

import com.example.bezishodnost.model.*;
import com.example.bezishodnost.pojo.ForTrainerTrainings;
import com.example.bezishodnost.pojo.FreeTrainings;
import com.example.bezishodnost.pojo.MyTraining;
import com.example.bezishodnost.repo.TrainingRepo;
import com.example.bezishodnost.repo.TrainingTimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TrainingService {
    TrainingRepo trainingRepo;
    ScheduleService scheduleService;
    TrainingTimeRepo trainingTimeRepo;
    PersonService personService;

    public Training findById(int id){
        return trainingRepo.findById(id).get();
    }

    public void delete(Training training){
        trainingRepo.delete(training);
    }

    public ArrayList<FreeTrainings> findFreeTraining(Trainer trainer, Date date) {
        ArrayList<Schedule> scheduleArrayList = scheduleService.getScheduleByTrainer(trainer, date);
        ArrayList<TrainingTime> trainingTimes = new ArrayList<>();
        ArrayList<FreeTrainings> freeTrainings = new ArrayList<>();
        trainingTimeRepo.findAll().forEach(trainingTimes::add);
        for (Schedule schedule : scheduleArrayList) {
            ArrayList<TrainingTime> freeTimes = new ArrayList<>();
            for (TrainingTime trainingTime : trainingTimes) {
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

    public ArrayList<MyTraining> findByUser(User user) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        ArrayList<MyTraining> myTraining = new ArrayList<>();
        ArrayList<Training> trainings = new ArrayList<>();
        trainingRepo.findAllByUser(user).forEach(trainings::add);
        for (Training tr : trainings) {
            if (tr.getSchedule().getDate().compareTo(date) == 1) {
                MyTraining mt = new MyTraining();
                mt.setTrainingTime(tr.getTrainingTime());
                mt.setSchedule(tr.getSchedule());
                mt.setTrainer(personService.findByUser(tr.getSchedule().getTrainer().getUser()));
                mt.setGym(tr.getSchedule().getGym());
                mt.setId(tr.getTrainingId());
                mt.setU(tr.getSchedule().getTrainer().getUser());
                myTraining.add(mt);
            }
        }
        return myTraining;
    }

    public ArrayList<ForTrainerTrainings> forTrainer(ArrayList<Schedule> arrayList) {
        ArrayList<ForTrainerTrainings> trainings = new ArrayList<>();

        for (Schedule s : arrayList) {
            ForTrainerTrainings ftt = new ForTrainerTrainings();
            ftt.setSchedule(s);
            ArrayList<MyTraining> myTrainings = new ArrayList<>();
            ArrayList<Training> t = new ArrayList<>();
            trainingRepo.findAllByScheduleOrderByTrainingTime(s).forEach(t::add);

            for (Training training : t) {
                MyTraining mt = new MyTraining();

                mt.setTrainer(personService.findByUser(training.getUser()));
                mt.setTrainingTime(training.getTrainingTime());
                mt.setGym(training.getSchedule().getGym());
                mt.setU(training.getUser());
                mt.setId(training.getTrainingId());
                myTrainings.add(mt);

            }
            ftt.setMyTrainings(myTrainings);
            trainings.add(ftt);
        }



        return trainings;
    }

    public void save(Training training) {
        trainingRepo.save(training);
    }

}
