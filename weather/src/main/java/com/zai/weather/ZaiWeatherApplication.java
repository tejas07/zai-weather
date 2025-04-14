package com.zai.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zai.weather")
public class ZaiWeatherApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZaiWeatherApplication.class, args);
	}
}
