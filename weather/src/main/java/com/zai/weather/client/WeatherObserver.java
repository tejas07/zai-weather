    package com.zai.weather.client;

    import com.zai.weather.model.WeatherResponse;

    public interface WeatherObserver {
        WeatherResponse fetchWeather(String city) throws Exception;
        String getName();
    }