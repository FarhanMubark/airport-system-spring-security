package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Model.Passenger;
import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import com.example.airportsystemspringsecurity.Repository.PassengerRepository;
import com.example.airportsystemspringsecurity.Repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    User user;
    @InjectMocks
    PassengerService passengerService;

    @InjectMocks
    PassengerRepository passengerRepository;

    @InjectMocks
    AuthRepository authRepository;

    Passenger passenger1;

    List<Passenger> passengers;
    @BeforeEach
    void setUp(){
        passenger1 = new Passenger(null,"khalid","23","male",user);

    }
    @Test
    void getPassenger() {
        when(passengerRepository.findAll()).thenReturn(passengers);
        List<Passenger> passengerList = passengerService.getAll();
        Assertions.assertEquals(passengerList,passengers);

    }


}