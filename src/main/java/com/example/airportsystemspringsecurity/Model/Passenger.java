package com.example.airportsystemspringsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    private String age;


    @NotEmpty
    @Column(columnDefinition = "varchar(7)",nullable = false)
    private String gender;



    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


}