package com.example.airportsystemspringsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;


    private String gate_number;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plane_number", referencedColumnName = "number")
    @JsonIgnore
    private Plane plane;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
//    private Set<Ticket> tickets;


}