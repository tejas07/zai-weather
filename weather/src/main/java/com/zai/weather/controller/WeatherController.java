package com.zai.weather.controller;

import com.zai.weather.model.WeatherResponse;
import com.zai.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/v1/weather")
    public WeatherResponse getWeather(@RequestParam(value = "city", defaultValue = "melbourne") String city) {
        return weatherService.getWeather(city);
    }
}
