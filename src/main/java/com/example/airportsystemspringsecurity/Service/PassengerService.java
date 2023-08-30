package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Api.ApiException;
import com.example.airportsystemspringsecurity.DTOs.PassengerDTO;
import com.example.airportsystemspringsecurity.Model.Passenger;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import com.example.airportsystemspringsecurity.Repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    final private PassengerRepository passengerRepository;
    final private AuthRepository authRepository;
    public List<Passenger> getAll(){
        return passengerRepository.findAll();
    }


    public void addPassenger(PassengerDTO passengerDTO) {
        User user = authRepository.findUserById(passengerDTO.getUser_id());

        if (user == null) {
            throw new ApiException("No User Found");
        }

        Passenger passenger = new Passenger();
        passenger.setGender(passengerDTO.getGender());
        passenger.setAge(passengerDTO.getAge());
        passenger.setUser(user);

        passengerRepository.save(passenger);
    }


    public void updatePassenger(Integer id, Passenger passenger){
        Passenger oldpass = passengerRepository.findPassengerById(id);

        if (oldpass==null){
            throw new ApiException("Passenger Not found with Id " + id);
        }

        oldpass.setName(passenger.getName());
        oldpass.setId(passenger.getId());

        passengerRepository.save(oldpass);
    }

    public void deletePassenger(Integer id){
        Passenger passenger = passengerRepository.findPassengerById(id);

        if (passenger == null) {
            throw new ApiException("Passenger Not found with Id " + id);
        }

        passengerRepository.delete(passenger);
    }

    public Passenger findById(Integer id){
        Passenger passenger= passengerRepository.findPassengerById(id);

        if (passenger==null){
            throw new ApiException("Passenger Not found with Id " + id);
        }
        return passenger;
    }




}