package com.zai.weather.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI weatherServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather Forecast API")
                        .version("v1")
                        .description("Spring Boot REST API for weather data using WeatherStack and OpenWeatherMap")
                        .contact(new Contact()
                                .name("Tejas Gowda")
                                .email("tejas7111991@gmail.com")
                        )
                );
    }
}
