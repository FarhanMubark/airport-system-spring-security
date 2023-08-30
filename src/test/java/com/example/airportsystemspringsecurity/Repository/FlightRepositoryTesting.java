package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.Plane;
import com.example.airportsystemspringsecurity.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@DataJpaTest // mean this JPA only for testing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightRepositoryTesting {

    @Autowired
    FlightRepository flightRepository;

    Plane plane;
    User user;
    Flight flight1;

    @BeforeEach
    void setUp() {
        user = new User(1, "farhan", "12344", "ADMIN", null, null, null);
        plane = new Plane(null, "airbus", "public", 200, null);
        flight1 = new Flight(null, "C7", user, plane);
    }

    Flight flight;

    @Test
    public void findFlightByNumber() {
        flightRepository.save(flight1);
        flight = flightRepository.findByNumber(flight.getNumber());
        Assertions.assertThat(flight).isEqualTo(flight1);
    }

}