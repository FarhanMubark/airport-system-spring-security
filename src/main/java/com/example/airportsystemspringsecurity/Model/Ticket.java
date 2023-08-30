package com.example.airportsystemspringsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    public Ticket(Integer number, String passengerName, String passengerClass, String from, String to, String  date, User user) {
        this.number = number;
        this.passengerName = passengerName;
        this.passengerClass = passengerClass;
        this.from = from;
        this.to = to;
        this.date = date;

    }


    private String passengerName;

    private String passengerClass;


    private String from;

    private String to;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_number", referencedColumnName = "number")
    private Flight flight;


}