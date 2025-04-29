package com.zai.weather.controller;

import com.zai.weather.model.WeatherResponse;
import com.zai.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Weather API", description = "Fetch weather info from APIs")
@RestController
@Slf4j
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @Operation(summary = "Get weather details by city", description = "Returns wind speed and temperature of a given city. Defaults to Melbourne.")
    @GetMapping("/v1/weather")
    public WeatherResponse getWeather(
            @Parameter(description = "City name", example = "melbourne")
            @RequestParam(value = "city", defaultValue = "melbourne") String city) {
        log.info("getWeather() {}",city);
        return weatherService.getWeather(city);
    }
}
