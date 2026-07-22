package com.weatherapp.weatherapp.service;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public String getWeather(String city){
        return "weather data from service for" + city;

    }
}
