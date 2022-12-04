package com.example.bezishodnost;

import com.example.bezishodnost.model.User;
import com.example.bezishodnost.repo.UserRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepoTests {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void findById() {
        User user = userRepo.findById(5l).get();
        assertEquals(user.getUserId(), 5l);
    }

    @Test
    public void findByIdNegative() {
        assertFalse(userRepo.findById(512l).isPresent());
    }

    @Test
    public void findByLogin() {
        User user = userRepo.findByLogin("archik43").get();
        assertEquals("archik43", user.getLogin());
    }

    @Test
    public void findByLoginNegative() {
        assertFalse(userRepo.findByLogin("archik431").isPresent());
    }
}
