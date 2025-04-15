    package com.zai.weather.client;

    import com.zai.weather.model.WeatherResponse;

    public interface WeatherStrategy {
        WeatherResponse fetch(String city) throws Exception;
        String getName();
    }