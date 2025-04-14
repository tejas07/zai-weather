package com.zai.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse implements Serializable {

    private double windSpeed;
    private double temperatureDegrees;
}
