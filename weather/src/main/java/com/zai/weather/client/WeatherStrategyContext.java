package com.zai.weather.client;

import com.zai.weather.exception.ApiDownException;
import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WeatherStrategyContext {

    private final List<WeatherStrategy> strategies = new ArrayList<>();

    public void attach(WeatherStrategy observer) {
        strategies.add(observer);
    }

    public WeatherResponse getWeather(String city) {
        for (WeatherStrategy strategy : strategies) {
            try {
                return strategy.fetch(city);
            } catch (Exception e) {
                log.error( "failed {}, try fetching next ...",strategy.getName());
            }
        }
        throw new ApiDownException("Both weather APIs are currently unavailable. Please try again later.");
    }
}