package com.weatherapp.weatherapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//this class will handle exxception coming from our REST contollers/application flow
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)//if a citynotfound occurs run handlecitynotfound
    public ResponseEntity<Map<String , String>> handleCityNotFound(CityNotFoundException e) {

        Map<String , String> error = new HashMap<>();
        error.put("message" , e.getMessage());

        return ResponseEntity
                .status(404)
                .body(error);
    }
}