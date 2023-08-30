package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Api.ApiResponse;
import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Service.FlightService;
import com.example.airportsystemspringsecurity.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;


    @GetMapping("/get-ticket")
   public ResponseEntity getAllTickets(@AuthenticationPrincipal User user){
       return ResponseEntity.status(200).body(ticketService.getAllTicket(user.getId()));
   }

   @PostMapping("/add-ticket")
   public ResponseEntity addTickets(@AuthenticationPrincipal User user, @RequestBody Ticket ticket){
       ticketService.addTicket(user.getId(), ticket);
       return ResponseEntity.status(200).body(new ApiResponse("Ticket added"));
   }

   @PutMapping("/update-ticket/{ticket_number}")
   public ResponseEntity updateTicket(@AuthenticationPrincipal User user, @RequestBody Ticket ticket, @PathVariable Integer ticket_number){
        ticketService.updateTicket(user.getId(), ticket_number, ticket);
       return ResponseEntity.status(200).body(new ApiResponse("Ticket updated"));
   }

   @DeleteMapping("/delete-ticket/{ticket_number}")
   public ResponseEntity deleteTicket(@AuthenticationPrincipal User user, @PathVariable Integer ticket_number){
        ticketService.deleteTicket(user.getId(), ticket_number);
       return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted"));
   }

   @PutMapping("/{flight_number}/assign/{ticket_number}")
   public ResponseEntity assignTeckitToFlight(@PathVariable Integer flight_number, @PathVariable Integer ticket_number){
        ticketService.assignTeckitToFlight(flight_number, ticket_number);
       return ResponseEntity.status(200).body(new ApiResponse("Assign Done"));
   }
}
