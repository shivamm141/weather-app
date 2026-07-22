package com.weatherapp.weatherapp.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.weatherapp.weatherapp.dto.OpenWeatherResponse;


@Service
public class WeatherService {

    @Value("${weather.api.key}")//find the configration property called weather,api,key and put its value into tis variable
    private String apiKey;

    private final RestClient restClient; // used to send http request
    public WeatherService() {
    this.restClient = RestClient.create();
    }

    public OpenWeatherResponse getWeather(String city){

        OpenWeatherResponse response = restClient.get() //use our resclient object to prepare an http get request
        .uri ("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric",
                    city, apiKey)
        .retrieve()//senf the request and prepare for the reponse
        .body(OpenWeatherResponse.class); // whateer maybe the respone give it in string
        return response;

    }
}
