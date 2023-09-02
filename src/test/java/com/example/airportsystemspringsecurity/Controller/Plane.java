package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Service.PlaneService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PlaneController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class Plane {
    @MockBean
    PlaneService planeService;

    @Autowired
    MockMvc mockMvc;

    Plane p1, p2;
    User user;


    List<Plane> planes, planeList;

    @BeforeEach
    void setUp(){
        user = new User(1,"Farhan","123456","ADMIN",null);
        p1 = new Plane(null, "airbus", "public", 200, null);
        p2 = new Plane(null, "airbus", "public", 300, null);


        planes= Arrays.asList(p1);
        planeList=Arrays.asList(p2);
    }


    @Test
    public void GetAllTplanes() throws Exception {
        Mockito.when(planeService.getAll()).thenReturn(planes);
        mockMvc.perform(get("/api/v1/plane/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("public"));
    }


}
