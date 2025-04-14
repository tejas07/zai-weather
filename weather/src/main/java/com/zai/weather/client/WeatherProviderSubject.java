package com.zai.weather.client;

import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WeatherProviderSubject {

    private final List<WeatherObserver> observers = new ArrayList<>();

    public void attach(WeatherObserver observer) {
        observers.add(observer);
    }

    public WeatherResponse notifyObservers(String city) {
        for (WeatherObserver observer : observers) {
            try {
                return observer.fetchWeather(city);
            } catch (Exception e) {
                log.info( "failed {}, try fetching next ...",observer.getName());
            }
        }
        throw new RuntimeException("All providers failed.");
    }
}