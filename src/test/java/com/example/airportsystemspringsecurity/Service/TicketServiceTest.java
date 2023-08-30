package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import com.example.airportsystemspringsecurity.Repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    User user;

    Ticket ticket1;
    Ticket ticket2;

    List<Ticket> tickets;
    @InjectMocks
   TicketService ticketService;

    @InjectMocks
    TicketRepository ticketRepository;

    @InjectMocks
    AuthRepository authRepository;

    @BeforeEach
    void setUp(){
        user = new User(1,"farhan","12344","ADMIN",null);
        ticket1 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2080",user);
        ticket2 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2090",user);

        tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
    }


    @Test
    void getAllTicket() {
        when(ticketRepository.findAll()).thenReturn(tickets);
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        List<Ticket> ticketList = ticketService.getAllTicket(user.getId());
        Assertions.assertEquals(ticketList,tickets);
    }


    @Test
    void addTicket() {
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        ticketService.addTicket(user.getId(), ticket1);
        verify(ticketRepository,times(1)).save(ticket1);
        verify(authRepository,times(1)).findUserById(user.getId());
    }


    @Test
    void updateTicket() {
        when(ticketRepository.findTicketByNumber(ticket1.getNumber())).thenReturn(ticket1);
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        ticketService.updateTicket(user.getId(),ticket1.getNumber(),ticket2);
        verify(ticketRepository,times(1)).findTicketByNumber(ticket1.getNumber());
        verify(authRepository,times(1)).findUserById(user.getId());
        verify(ticketRepository,times(1)).save(ticket1);
    }

    @Test
    void deleteTicket() {
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(ticketRepository.findTicketByNumber(ticket1.getNumber())).thenReturn(ticket1);
        ticketService.deleteTicket(ticket1.getNumber(), user.getId());
        verify(ticketRepository,times(1)).save(ticket1);
        verify(authRepository,times(1)).findUserById(user.getId());
    }
}