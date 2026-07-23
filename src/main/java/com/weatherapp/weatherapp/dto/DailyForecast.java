package com.weatherapp.weatherapp.dto;

public class DailyForecast {

    private String date;
    private double minTemperature;
    private double maxTemperature;
    private String description;

    public DailyForecast(
            String date,
            double minTemperature,
            double maxTemperature,
            String description) {

        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String getDescription() {
        return description;
    }
}