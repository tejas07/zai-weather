package com.zai.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testWeatherApi_WithValidCity() throws Exception {
        mockMvc.perform(get("/v1/weather")
                        .param("city", "melbourne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperatureDegrees").exists())
                .andExpect(jsonPath("$.windSpeed").exists());
    }

    @Test
    void testWeatherApi_WithDefaultCity() throws Exception {
        mockMvc.perform(get("/v1/weather"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperatureDegrees").exists())
                .andExpect(jsonPath("$.windSpeed").exists());
    }

    @Test
    void testWeatherApi_WithDefaultCity_ThrowsException() throws Exception {
        mockMvc.perform(get("/v1/weather")
                        .param("city", (String) null))
                .andExpect(status().isOk());
    }

    // Intentially commented as to test this internet need to be disconnected
   /* @Test
    void testWeatherApi_WhenBothApisDown_ShouldReturnError() throws Exception {
        // You'd need to manually turn off both APIs or mock them to simulate failure
        // For real E2E, temporarily break API keys or disconnect internet here
        mockMvc.perform(get("/v1/weather")
                        .param("city", "testcity"))
                .andExpect(status().isBadGateway())
                .andExpect(jsonPath("$.error", is("All providers failed and no cached data available.")));
    }*/
}
