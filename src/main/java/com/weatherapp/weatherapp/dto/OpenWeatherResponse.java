
package com.weatherapp.weatherapp.dto;
import java.util.List;



public class OpenWeatherResponse {
    private List<WeatherDescription> weather;

    private String name;
    private MainWeatherData main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainWeatherData getMain() {
        return main;
    }

    public void setMain(MainWeatherData main) {
        this.main = main;
    }
    public List<WeatherDescription> getWeather() {
    return weather;
}

    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }
    
    
    
    
}
