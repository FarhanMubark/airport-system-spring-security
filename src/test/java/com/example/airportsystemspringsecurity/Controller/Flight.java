package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TicketController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class Flight {
    @MockBean
    TicketService ticketService;

    @Autowired
    MockMvc mockMvc;
    Ticket t1, t2, t3;
    User user;


    List<Ticket> tickets, ticketList;

    @BeforeEach
    void setUp(){
        user = new User(1,"Farhan","123456","ADMIN",null);
        t1 = new Ticket(null,"farhan","ECO","RIYADH","HAIL","12/12/2080",user);
        t2 = new Ticket(null,"khalid","ECO","RIYADH","HAIL","12/12/2090",user);
        t3 = new Ticket(null,"majed","ECO","RIYADH","HAIL","12/12/2030",user);


        tickets= Arrays.asList(t1);
        ticketList=Arrays.asList(t2);
    }


    @Test
    public void GetAllTicket() throws Exception {
        Mockito.when(ticketService.getAllTicket()).thenReturn(tickets);
        mockMvc.perform(get("/api/v1/ticket/get-ticket"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].passenger").value("farhan"));
    }

    @Test
    public void testAddTicket() throws Exception{
        mockMvc.perform(post("/api/v1/ticket/add-ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(t1)))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteTicket() throws Exception{
        mockMvc.perform(delete("/api/v1/ticket/delete-ticket/{id}",t1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception{
        mockMvc.perform(put("/api/v1/ticket/update-ticket/{id}",t1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(t1)))
                .andExpect(status().isOk());
    }
}
