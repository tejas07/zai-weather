package com.zai.weather;

import com.zai.weather.cache.WeatherCache;
import com.zai.weather.client.WeatherStrategy;
import com.zai.weather.client.WeatherStrategyContext;
import com.zai.weather.exception.ApiDownException;
import com.zai.weather.model.WeatherResponse;
import com.zai.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherApplicationUnitTests {

	private WeatherStrategy weatherStack;
	private WeatherStrategy openWeatherMap;
	private WeatherStrategyContext subject;
	private WeatherCache cache;
	private WeatherService weatherService;

	@BeforeEach
	public void setup() {
		weatherStack = mock(WeatherStrategy.class);
		openWeatherMap = mock(WeatherStrategy.class);
		subject = new WeatherStrategyContext();
		subject.attach(weatherStack);
		subject.attach(openWeatherMap);
		cache = mock(WeatherCache.class);
		weatherService = new WeatherService(subject, List.of(weatherStack, openWeatherMap), cache);
	}

	@Test
	public void testPrimaryApiDown_SecondaryWorks() throws Exception {
		String city = "melbourne";
		when(weatherStack.fetch(city)).thenThrow(new RuntimeException("WeatherStack Down"));
		WeatherResponse response = new WeatherResponse(10.0, 22.0);
		when(openWeatherMap.fetch(city)).thenReturn(response);

		WeatherResponse result = weatherService.getWeather(city);

		assertEquals(response.getTemperatureDegrees(), result.getTemperatureDegrees());
		assertEquals(response.getWindSpeed(), result.getWindSpeed());
	}

	@Test
	public void testSecondaryApiDown_PrimaryWorks() throws Exception {
		String city = "melbourne";
		WeatherResponse response = new WeatherResponse(8.0, 18.5);
			when(weatherStack.fetch(city)).thenReturn(response);

		when(openWeatherMap.fetch(city)).thenThrow(new RuntimeException(" OpenWeatherMap should not be called"));

		WeatherResponse result = weatherService.getWeather(city);

		assertEquals(response.getTemperatureDegrees(), result.getTemperatureDegrees());
		assertEquals(response.getWindSpeed(), result.getWindSpeed());
	}

	@Test
	public void testBothApisDown_ThrowsApiDownException() throws Exception {
		String city = "melbourne";
		when(weatherStack.fetch(city)).thenThrow(new RuntimeException("Weather Stack down"));
		when(openWeatherMap.fetch(city)).thenThrow(new RuntimeException("Open Weather map down"));

		assertThrows(ApiDownException.class, () -> weatherService.getWeather(city));
	}

	@Test
	public void testNullCity_throwsNPE() throws Exception {

		assertThrows(NullPointerException.class, () -> {
			weatherService.getWeather(null);
		});
	}
}