package com.weatherapp.weatherapp.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.weatherapp.weatherapp.dto.WeatherResponse;
import com.weatherapp.weatherapp.dto.OpenWeatherResponse;
import com.weatherapp.weatherapp.exception.CityNotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import com.weatherapp.weatherapp.dto.ForecastApiResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.weatherapp.weatherapp.dto.ForecastItem;
import com.weatherapp.weatherapp.dto.DailyForecast;

@Service
public class WeatherService {

    @Value("${weather.api.key}")//find the configration property called weather,api,key and put its value into tis variable
    private String apiKey;

    private final RestClient restClient; // used to send http request
    public WeatherService() {
    this.restClient = RestClient.create();
    }

    public WeatherResponse getWeather(String city){
        if(city == null || city.trim().isEmpty()){
            throw new IllegalArgumentException("city name is required");
        }

        try{  //try and catch for error handling 
            OpenWeatherResponse response = restClient.get() //use our resclient object to prepare an http get request
            .uri ("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric",
                    city, apiKey)
            .retrieve()//senf the request and prepare for the reponse
            .body(OpenWeatherResponse.class); // whateer maybe the respone give it in string
        
            return new WeatherResponse(
            response.getName(),
            response.getMain().getTemp(),
            response.getMain().getHumidity(),
            response.getWeather().get(0).getDescription()
            );
        }catch (HttpClientErrorException.NotFound e){ // if throw httperror catch ctaches it and throw new city not found package constructor //specifically represent an http 404 not found
            throw new CityNotFoundException("City Not Found :  " + city);
        }

    }
    public List<DailyForecast> getForecast(String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name is required");
        }

        try {
            ForecastApiResponse response = restClient.get()
                    .uri(
                        "https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}&units=metric",
                        city, apiKey
                    )
                    .retrieve()
                    .body(ForecastApiResponse.class);

                Map<String, List<ForecastItem>> groupedByDate = new LinkedHashMap<>();

                for (ForecastItem item : response.getList()) {

                     String date = item.getDt_txt().substring(0, 10);

                    groupedByDate
                        .computeIfAbsent(date, k -> new ArrayList<>())
                        .add(item);
                }

            List<DailyForecast> dailyForecasts = new ArrayList<>();

            for (Map.Entry<String, List<ForecastItem>> entry : groupedByDate.entrySet()) {

                String date = entry.getKey();
                List<ForecastItem> items = entry.getValue();

                double minTemp = Double.MAX_VALUE;
                double maxTemp = -Double.MAX_VALUE;

                for (ForecastItem item : items) {
                    double temp = item.getMain().getTemp();

                    if (temp < minTemp) {
                        minTemp = temp;
                    }

                    if (temp > maxTemp) {
                        maxTemp = temp;
                    }
                }

                String description = items.get(0)
                        .getWeather()
                        .get(0)
                        .getDescription();

                dailyForecasts.add(
                    new DailyForecast(date, minTemp, maxTemp, description)
                );
            }

            return dailyForecasts.stream()
                .limit(5)
                .toList();

        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City not found: " + city);
        }
    }
}
