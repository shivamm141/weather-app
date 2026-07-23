package com.weatherapp.weatherapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weatherapp.weatherapp.dto.WeatherResponse;
import com.weatherapp.weatherapp.dto.DailyForecast;
import com.weatherapp.weatherapp.service.WeatherService;

@RestController //tells that this class handles rest/web request
public class WeatherController {

    private final WeatherService weatherService; //refernce variable point to weatherservice class
    public WeatherController(WeatherService weatherService) { // constructor // to creater my controller i need weatherservice obj you give me
    this.weatherService = weatherService; // refernce obj of service class used by controller class// contructor based dependecy injection
}

    @GetMapping("/weather") // this method handles Get request to /weather
    public WeatherResponse handleWeatherRequest(@RequestParam String city) {
        return weatherService.getWeather(city);//method of service class using obj refernce 
        
    }
    @GetMapping("/forecast")
    public List<DailyForecast> getForecast(@RequestParam String city) {
        return weatherService.getForecast(city);
    }
}

