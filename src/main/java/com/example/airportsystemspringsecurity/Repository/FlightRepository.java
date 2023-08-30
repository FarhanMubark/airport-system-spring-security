package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight findByNumber(Integer number);
    @Query("select f from Flight  f where f.gate_number = ?1 ")
    Flight findFlightByGate_number(String gate);


}
