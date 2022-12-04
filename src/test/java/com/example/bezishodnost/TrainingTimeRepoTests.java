package com.example.bezishodnost;

import com.example.bezishodnost.model.TrainingTime;
import com.example.bezishodnost.repo.TrainingTimeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TrainingTimeRepoTests {
    @Autowired
    private TrainingTimeRepo trainingTimeRepo;

    @Test
    public void findAll() {
        List<TrainingTime> trainingTimes = trainingTimeRepo.findAll();
        assertTrue(!trainingTimes.isEmpty());
    }

    @Test
    public void findById() {
        TrainingTime trainingTime = trainingTimeRepo.findById(1).get();
        assertEquals(1, trainingTime.getId());
    }

    @Test
    public void findByIdNegative() {
        assertFalse(trainingTimeRepo.findById(1111).isPresent());
    }
}
