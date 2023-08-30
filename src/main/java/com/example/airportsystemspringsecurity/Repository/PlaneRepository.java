package com.example.airportsystemspringsecurity.Repository;

import com.example.airportsystemspringsecurity.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    Plane findPlaneByNumber(Integer number);

    List<Plane> findPlaneByType(String type);


}
