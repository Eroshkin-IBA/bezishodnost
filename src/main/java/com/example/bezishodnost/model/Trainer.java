package com.example.bezishodnost.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Trainer {
    @Id
    @Column(name = "trainer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainerId;


    @ManyToOne
    @JoinColumn(name = "gym_Id", nullable = true)
    private Gym gym;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "experience")
    private int experience;


}
