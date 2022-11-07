package com.example.bezishodnost.service;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.Schedule;
import com.example.bezishodnost.model.Trainer;
import com.example.bezishodnost.pojo.SchedulePojo;
import com.example.bezishodnost.pojo.TrainerPojo;
import com.example.bezishodnost.repo.PersonRepo;
import com.example.bezishodnost.repo.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@Service
public class ScheduleService {
    ScheduleRepo scheduleRepo;
    TrainerService trainerService;
    private final PersonRepo personRepo;

    public void add(Schedule schedule){
        scheduleRepo.save(schedule);
    }

    public ArrayList<SchedulePojo> getScheduleByGym(Gym gym, Date date){
        ArrayList<SchedulePojo> scheduleArrayList = new ArrayList<>();
        for (Schedule schedule:scheduleRepo.findAllByGymAndDateIsGreaterThanOrderByDate(gym, date)) {
            SchedulePojo schedulePojo = new SchedulePojo();
            schedulePojo.setSchedule(schedule);
            Person person = new Person();

            person = personRepo.findByUser(schedule.getTrainer().getUser()).get();

            schedulePojo.setPerson(person);

            scheduleArrayList.add(schedulePojo);
        }

        return scheduleArrayList;
    }

    public ArrayList<Schedule> getScheduleByTrainer(Trainer trainer){
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
        scheduleRepo.findAllByTrainerOrderByDate(trainer).forEach(scheduleArrayList::add);
        return scheduleArrayList;
    }

}
