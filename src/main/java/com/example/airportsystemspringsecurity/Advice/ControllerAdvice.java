package com.example.airportsystemspringsecurity.Advice;

import com.example.airportsystemspringsecurity.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrity(DataIntegrityViolationException e) {
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = SQLSyntaxErrorException.class )
    public ResponseEntity SQLSyntaxErrorException(SQLSyntaxErrorException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }


    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        String message = e.getMessage();

        return ResponseEntity.status(200).body(message);
    }

}