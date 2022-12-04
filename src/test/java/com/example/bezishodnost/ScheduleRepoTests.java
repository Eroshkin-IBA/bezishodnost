package com.example.bezishodnost;

import com.example.bezishodnost.model.Schedule;
import com.example.bezishodnost.repo.GymRepo;
import com.example.bezishodnost.repo.ScheduleRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ScheduleRepoTests {
//    @Autowired
//    private ScheduleRepo scheduleRepo;
//
//    @Autowired
//    private GymRepo gymRepo;
//
//    @Test
//    public void findById(){
//        Schedule schedule = scheduleRepo.findById(12).get();
//        assertEquals(12, schedule.getId());
//    }
//
//    @Test
//    public void findByIdNegative(){
//        assertFalse(scheduleRepo.findById(12).isPresent());
//    }
//
//    @Test
//    public void findAllByGymAndDateIsGreaterThanOrderByDate() throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
//        Date date = formatter.parse("2022-11-10");
//        List<Schedule> schedules = scheduleRepo.findAllByGymAndDateIsGreaterThanOrderByDate(gymRepo.findById(1).get(),date );
//        assertTrue(schedules.isEmpty());
//    }
//
//    @Test
//    public void findAllByGymAndDateIsGreaterThanOrderByDateNegative() throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
//        Date date = formatter.parse("2044-11-10");
//        List<Schedule> schedules = scheduleRepo.findAllByGymAndDateIsGreaterThanOrderByDate(gymRepo.findById(1).get(),date );
//        assertTrue(schedules.isEmpty());
//    }
}
