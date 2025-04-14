package com.zai.weather.client;

import com.zai.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OpenWeatherMapClient implements WeatherObserver {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherResponse fetchWeather(String city) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,AU&appid=%s&units=metric", city, apiKey);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response != null && response.get("main") != null && response.get("wind") != null) {
            double temp = ((Number) ((Map<String, Object>) response.get("main")).get("temp")).doubleValue();
            double wind = ((Number) ((Map<String, Object>) response.get("wind")).get("speed")).doubleValue();
            return new WeatherResponse(wind, temp);
        }
        throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
    }

    @Override
    public String getName() {
        return "OpenWeatherMap";
    }
}