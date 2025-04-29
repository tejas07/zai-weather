package com.zai.weather.client;

import com.zai.weather.model.WeatherResponse;

public class WeatherStationInfo implements WeatherStrategy{
    @Override
    public WeatherResponse fetch(String city) throws Exception {
        //
        return null;
    }

    @Override
    public String getName() {
        return "WeatherStationInfo";
    }
}
