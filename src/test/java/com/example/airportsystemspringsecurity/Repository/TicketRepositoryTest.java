package com.example.airportsystemspringsecurity.Repository;

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

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest // mean this JPA only for testing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketRepositoryTest {
        @Autowired
        TicketRepository ticketRepository;

        User user;

        Ticket ticket1;
        Ticket ticket2;
        Ticket ticket3;

        List<Ticket> tickets;



      @BeforeEach
        void setUp(){
                user = new User(1,"farhan","12344","ADMIN",null);
                ticket1 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2080",user);
                ticket2 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2090",user);
                ticket3 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2030",user);
        }

        @Test
        public void FindAllByUserTest(){
          ticketRepository.save(ticket1);
          ticketRepository.save(ticket2);
          ticketRepository.save(ticket3);
          tickets = ticketRepository.findAllByUser(user);
          Assertions.assertThat(tickets.get(0).getUser().getId()).isEqualTo(user.getId());
        }


        Ticket ticket;
      @Test
        public void findTicketByNumber(){
          ticketRepository.save(ticket1);
          ticket = ticketRepository.findTicketByNumber(ticket1.getNumber());
          Assertions.assertThat(ticket).isEqualTo(ticket1);
        }
}