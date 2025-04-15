package com.zai.weather.service;

import com.zai.weather.cache.WeatherCache;
import com.zai.weather.client.WeatherStrategy;
import com.zai.weather.client.WeatherStrategyContext;
import com.zai.weather.exception.ApiDownException;
import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WeatherService {

    private final WeatherStrategyContext strategyContext;
    private final List<WeatherStrategy> strategies;
    private final WeatherCache cache;

    public WeatherService(WeatherStrategyContext strategyContext, List<WeatherStrategy> strategies, WeatherCache cache) {
        this.strategyContext = strategyContext;
        this.strategies = strategies;
        this.cache = cache;
    }

    @PostConstruct
    public void initStrategies() {
        strategies.forEach(strategyContext::attach);
    }

    public WeatherResponse getWeather(String city) {

        Optional.ofNullable(city)
                .ifPresentOrElse(
                        c -> {},
                        () -> {throw new NullPointerException("City can't be null");}
                );

        WeatherResponse cached = cache.get(city);
        if (cached != null) return cached;

        try {
            WeatherResponse response = strategyContext.getWeather(city);
            cache.set(city, response);
            return response;
        } catch (Exception e) {
            WeatherResponse stale = cache.getStale(city);
            if (stale != null) return stale;
            log.error("All providers failed and no cached data available.");
            throw new ApiDownException("All providers failed and no cached data available.");
        }
    }
}