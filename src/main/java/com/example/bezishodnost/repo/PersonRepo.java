package com.example.bezishodnost.repo;

import com.example.bezishodnost.model.Person;
import com.example.bezishodnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {
    public Optional<Person> findByUser(User user);
    @Transactional
    @Modifying
    @Query("update Person p set p.phone = :phone where p.Id = :id")
    int updatePhone(@Param(value = "phone") String phone, @Param(value = "id") Long id);

    @Transactional
    @Modifying
    @Query("update Person p set p.image = :image where p.Id = :id")
    int updatePhoto(@Param(value = "image") String image, @Param(value = "id") Long id);

}
