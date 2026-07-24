package com.weatherapp.weatherapp.dto;

public class MainWeatherData {
    private double temp;
    private int humidity;
    private double feels_like;
    private int pressure;

     public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public double getFeels_like(){
        return feels_like;
    }
    public void setFeels_like(double feels_like){
        this.feels_like = feels_like;
    }
    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
