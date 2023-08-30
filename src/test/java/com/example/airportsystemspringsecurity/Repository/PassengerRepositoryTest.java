package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.Passenger;
import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest // mean this JPA only for testing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PassengerRepositoryTest {

    @Autowired
    PassengerRepository passengerRepository;

    User user;
    Set<Ticket> ticket;
    Set<Flight> flight;

    Passenger passenger1;




    @BeforeEach
    void setUp(){
        user = new User(1,"farhan","12344","ADMIN",null,ticket,flight);
        passenger1 = new Passenger(null,"khalid","23","male",user);

    }

    Passenger passenger;
    @Test
    public void findPassengerById(){
        passengerRepository.save(passenger1);
        passenger = passengerRepository.findPassengerById(passenger.getId());
        Assertions.assertThat(passenger).isEqualTo(passenger1);
    }

}
