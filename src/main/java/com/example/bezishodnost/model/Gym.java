package com.example.bezishodnost.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name ="gym")
public class Gym implements Serializable {
    @javax.persistence.Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String city;
    private String street;
    private String house;


    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


}