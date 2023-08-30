package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Api.ApiException;
import com.example.airportsystemspringsecurity.Model.Flight;
import com.example.airportsystemspringsecurity.Model.Plane;
import com.example.airportsystemspringsecurity.Model.Ticket;
import com.example.airportsystemspringsecurity.Repository.FlightRepository;
import com.example.airportsystemspringsecurity.Repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    final private FlightRepository flightRepository;
    final private PlaneRepository planeRepository;

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

    public void addFlight(Flight flight){
        flightRepository.save(flight);
    }

    public void updateFlight(Integer number, Flight flight){
        Flight oldFlight = flightRepository.findByNumber(number);

        if (oldFlight==null){
            throw new ApiException("No flight found with number" + number);
        }

        oldFlight.setNumber(flight.getNumber());
        flightRepository.save(oldFlight);
    }

    public void deleteFlight(Integer number){
        Flight flight = flightRepository.findByNumber(number);

        if (flight==null){
            throw new ApiException("No flight found with number" + number);
        }

        flightRepository.delete(flight);

    }

    public Flight getByGate(String gate){
        Flight flight = flightRepository.findFlightByGate_number(gate);

        if (flight==null){
            throw new ApiException("No flight for this gate number "+gate );
        }
        return flight;
    }

    public Flight getFlightByNumber(Integer number){
        Flight flight = flightRepository.findByNumber(number);

        if (flight==null){
            throw new ApiException("No flight found with number" + number);
        }

        return flight;
    }



    public void assignPlaneToFlight(Integer plane_number, Integer flight_number){
        Plane plane = planeRepository.findPlaneByNumber(plane_number);
        Flight flight = flightRepository.findByNumber(flight_number);

                if (plane == null || flight == null){
            throw new ApiException("Assign Fail");
        }
                flight.setPlane(plane);
                flightRepository.save(flight);

    }
}