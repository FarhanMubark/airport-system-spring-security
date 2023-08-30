package com.example.airportsystemspringsecurity.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    private String name;

    private String type;

    private Integer capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plane")
    private Set<Flight> flights;



}