package com.example.bezishodnost.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String name;
    private String surName;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;

    private int sex;


}
