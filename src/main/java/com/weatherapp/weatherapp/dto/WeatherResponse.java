package com.weatherapp.weatherapp.dto; // jackson dto for outgoing api 

public class WeatherResponse {
    private String description;
    private String city;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double feelsLike;
    private int pressure; 
    private int visibility;
    private long sunrise;
    private long sunset;

    public WeatherResponse(String city, double temperature, int humidity , String description , double windSpeed , double feelsLike , int pressure , int visibilty , long sunrise , long sunset) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.visibility = visibilty;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }
    public String getDescription(){
        return description;
    }
    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }
    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
}
