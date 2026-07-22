package com.weatherapp.weatherapp.dto;

public class OpenWeatherResponse {

    private String name;
    private mainWeatherData main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public mainWeatherData getMain() {
        return main;
    }

    public void setMain(mainWeatherData main) {
        this.main = main;
    }
    
}
