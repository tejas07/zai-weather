package com.zai.weather.client;

import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class WeatherStackStrategy implements WeatherStrategy {

    @Value("${weatherstack.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherResponse fetch(String city) {
        log.info("WeatherStackClient executing...");

        String url = String.format("http://api.weatherstack.com/current?access_key=%s&query=%s", apiKey, city);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response != null && response.get("current") != null) {
            Map<String, Object> current = (Map<String, Object>) response.get("current");
            double temp = ((Number) current.get("temperature")).doubleValue();
            double wind = ((Number) current.get("wind_speed")).doubleValue();
            return new WeatherResponse(wind, temp);
        }
        throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
    }

    @Override
    public String getName() {
        return "WeatherStack";
    }
}