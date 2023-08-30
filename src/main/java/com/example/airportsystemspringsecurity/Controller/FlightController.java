package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Api.ApiResponse;
import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/get")
    public ResponseEntity getall(){
        return ResponseEntity.status(200).body(flightService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addFlight(@RequestBody @Valid Flight flight){
        flightService.addFlight(flight);
        return ResponseEntity.status(200).body(new ApiResponse("Flight Added"));
    }

    @PutMapping("/update/{number}")
    public ResponseEntity updateFlight(@PathVariable int number, @RequestBody Flight flight){
        flightService.updateFlight(number,flight);

        return ResponseEntity.status(200).body(new ApiResponse("Flight Updated"));
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity deleteFlight(@PathVariable int number){
        flightService.deleteFlight(number);

        return ResponseEntity.status(200).body(new ApiResponse("Flight Deleted"));
    }
    @GetMapping("/get-by-gate/{gate}")
    public ResponseEntity getByGate(@PathVariable String gate){
        Flight flight = flightService.getByGate(gate);
        return ResponseEntity.status(200).body(flight);
    }

    @GetMapping("get-by-number/{number}")
    public  ResponseEntity getByNumber(@PathVariable int number){
        Flight flight = flightService.getFlightByNumber(number);
        return ResponseEntity.status(200).body(flight);
    }

    @PutMapping("/{plane_number}/assign/{flight_number}")
    public ResponseEntity assginPlaneToFlight(@PathVariable Integer plane_number, @PathVariable Integer flight_number){
        flightService.assignPlaneToFlight(plane_number, flight_number);
        return ResponseEntity.status(200).body(new ApiResponse("Assign Done"));
    }
}

