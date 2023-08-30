package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Api.ApiException;
import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.Plane;
import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import com.example.airportsystemspringsecurity.Repository.FlightRepository;
import com.example.airportsystemspringsecurity.Repository.PlaneRepository;
import com.example.airportsystemspringsecurity.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
   private final TicketRepository ticketRepository;
   private final AuthRepository authRepository;
    private final FlightRepository flightRepository;


    public List<Ticket> getAllTicket(Integer user_id){
        User user = authRepository.findUserById(user_id);
        return ticketRepository.findAllByUser(user);
    }

    public void addTicket(Integer user_id, Ticket ticket){
        User user = authRepository.findUserById(user_id);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }


    public void updateTicket(Integer user_id, Integer ticket_number, Ticket ticket){
        User user = authRepository.findUserById(user_id);
        Ticket ticket1 = ticketRepository.findTicketByNumber(ticket_number);

        if (ticket1 != null && user.getId().equals(ticket1.getUser().getId())){
            ticket1.setDate(ticket.getDate());
            ticket1.setFrom(ticket.getFrom());
            ticket1.setTo(ticket.getTo());
            ticketRepository.save(ticket1);
        }else {
            throw new ApiException("ID Not Found");
        }
    }
    public void deleteTicket(Integer user_id, Integer ticket_number){
        User user = authRepository.findUserById(user_id);
        Ticket ticket = ticketRepository.findTicketByNumber(ticket_number);

        if (ticket != null && user.getId().equals(ticket.getUser().getId())){
            ticketRepository.delete(ticket);
        } else {
            throw new ApiException("ID Not Found");
        }
    }


    public void assignTeckitToFlight(Integer flight_number, Integer ticket_number){
        Flight flight = flightRepository.findByNumber(flight_number);
        Ticket ticket = ticketRepository.findTicketByNumber(ticket_number);

        if (flight == null || ticket == null){
            throw new ApiException("Assign Fail");
        }

        ticket.setFlight(flight);
        ticketRepository.save(ticket);
    }



}
