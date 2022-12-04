package com.example.bezishodnost;

import com.example.bezishodnost.model.Gym;
import com.example.bezishodnost.repo.GymRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class GymRepoTests {
    @Autowired
    private GymRepo gymRepo;

    @Test
    public void findByIdPositive(){
        Gym gym = gymRepo.findById(1).get();
        assertEquals(gym.getId(), 1);
    }

    @Test
    public void findByIdNegative(){
        assertFalse(gymRepo.findById(1000).isPresent());
    }

    @Test
    public void findAll(){
        ArrayList<Gym> gyms = new ArrayList<>();
        gymRepo.findAll().forEach(gyms::add);
        assertTrue(gyms.size()>0);
    }
}
