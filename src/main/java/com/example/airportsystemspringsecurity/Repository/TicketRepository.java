package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket, Integer> {

    Ticket findTicketByNumber(Integer number);
    List<Ticket> findAllByUser(User user);


}
