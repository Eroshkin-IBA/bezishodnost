package com.example.bezishodnost.pojo;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.Schedule;
import lombok.Data;

@Data
public class SchedulePojo {
    private Schedule schedule;
    private Person person;
}
