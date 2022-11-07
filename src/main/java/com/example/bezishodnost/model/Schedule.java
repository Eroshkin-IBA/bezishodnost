package com.example.bezishodnost.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;



}
