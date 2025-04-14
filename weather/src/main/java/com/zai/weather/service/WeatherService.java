package com.zai.weather.service;

import com.zai.weather.cache.WeatherCache;
import com.zai.weather.client.WeatherObserver;
import com.zai.weather.client.WeatherProviderSubject;
import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class WeatherService {

    private final WeatherProviderSubject providerSubject;
    private final List<WeatherObserver> observers;
    private final WeatherCache cache;

    public WeatherService(WeatherProviderSubject providerSubject, List<WeatherObserver> observers, WeatherCache cache) {
        this.providerSubject = providerSubject;
        this.observers = observers;
        this.cache = cache;
    }

    @PostConstruct
    public void initObservers() {
        observers.forEach(providerSubject::attach);
    }

    public WeatherResponse getWeather(String city) {
        WeatherResponse cached = cache.get(city);
        if (cached != null) return cached;

        try {
            WeatherResponse response = providerSubject.notifyObservers(city);
            cache.set(city,response);
            return response;
        } catch (Exception e) {
            WeatherResponse stale = cache.getStale(city);
            if (stale != null) return stale;
            log.error("All providers failed and no cached data available.");
            throw new RuntimeException("All providers failed and no cached data available.");
        }
    }
}