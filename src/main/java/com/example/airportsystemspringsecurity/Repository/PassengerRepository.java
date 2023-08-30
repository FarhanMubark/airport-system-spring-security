package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    Passenger findPassengerById(Integer id);



}
