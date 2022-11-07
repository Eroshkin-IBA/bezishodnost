package com.example.bezishodnost.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trainingTime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TrainingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String startTime;
    private String endTime;

}
