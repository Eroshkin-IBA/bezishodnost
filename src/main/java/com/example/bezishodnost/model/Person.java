package com.example.bezishodnost.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
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
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bDate;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;

    private int sex;

    public String getAge(){
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(bDate));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        int lastChar = Integer.parseInt((""+age).substring((""+age).length()-1, (""+age).length()));
        String old = "";
        if (age==11) old = "лет";
        else if((""+age).endsWith("1")) old = "год";
        else if(age>11 && age<15) old = "лет";
        else if(lastChar>1 && lastChar<5) old = "года";
        else old = "лет";
        return age + " " + old;
    }


}
