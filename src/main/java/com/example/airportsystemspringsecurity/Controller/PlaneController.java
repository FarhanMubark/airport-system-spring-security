package com.example.airportsystemspringsecurity.Controller;

import com.example.airportsystemspringsecurity.Api.ApiResponse;
import com.example.airportsystemspringsecurity.Model.Plane;
import com.example.airportsystemspringsecurity.Service.PlaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plane")
public class PlaneController {

    final private PlaneService planeService;

    @GetMapping("/get")
    public ResponseEntity getAll(){

        return ResponseEntity.status(200).body(planeService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addPlane(@RequestBody @Valid Plane plane){
        planeService.addPlane(plane);
        return ResponseEntity.status(200).body(new ApiResponse("Plane Added"));
    }

    @PutMapping("/update/{number}")
    public ResponseEntity updatePlane(@PathVariable int number, @RequestBody @Valid Plane plane){
        planeService.updatePlane(number,plane);
        return ResponseEntity.status(200).body(new ApiResponse("Plane Updated"));
    }

    @GetMapping("/get-by-type/{type}")
    public ResponseEntity getbyType(@PathVariable String type){
        List<Plane> plane = planeService.getPlaneByType(type);

        return ResponseEntity.status(200).body(plane);
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity deletePlane(@PathVariable int number){
        planeService.deletePlane(number);
        return ResponseEntity.status(200).body(new ApiResponse("Plane Deleted"));
    }

    @GetMapping("/get-by-number/{number}")
    public ResponseEntity getByNumber( @PathVariable int number){
        Plane plane = planeService.getPlaneByNumber(number);

        return ResponseEntity.status(200).body(plane);
    }
}