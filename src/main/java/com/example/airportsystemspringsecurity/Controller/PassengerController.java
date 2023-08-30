package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Api.ApiResponse;
import com.example.airportsystemspringsecurity.DTOs.PassengerDTO;
import com.example.airportsystemspringsecurity.Model.Passenger;
import com.example.airportsystemspringsecurity.Service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/passenger")
@RequiredArgsConstructor
public class PassengerController {

    final private PassengerService passengerService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(passengerService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addPass(@RequestBody @Valid PassengerDTO passengerDTO){
        passengerService.addPassenger(passengerDTO);

        return ResponseEntity.status(200).body("Passenger Added !");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePass(@PathVariable int id, @RequestBody @Valid Passenger passenger){
        passengerService.updatePassenger(id,passenger);
        return ResponseEntity.status(200).body(new ApiResponse("Passenger updated !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePass(@PathVariable int id){
        passengerService.deletePassenger(id);
        return ResponseEntity.status(200).body(new ApiResponse("Passenger Deleted !"));
    }



    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Passenger passenger = passengerService.findById(id);

        return ResponseEntity.status(200).body(passenger);
    }


}