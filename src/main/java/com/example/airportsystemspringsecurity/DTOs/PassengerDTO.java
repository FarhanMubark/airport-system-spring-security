package com.example.airportsystemspringsecurity.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassengerDTO {
    @NotNull
    private Integer user_id;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String age;
}
