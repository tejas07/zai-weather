package com.zai.weather.cache;

import com.zai.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class WeatherCache {

    private static final String CACHE_KEY_PREFIX = "weather:";
    private static final Duration TTL = Duration.ofSeconds(3);

    private final RedisTemplate<String, WeatherResponse> redisTemplate;
    private final ValueOperations<String, WeatherResponse> valueOps;

    @Autowired
    public WeatherCache(RedisTemplate<String, WeatherResponse> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
    }

    public WeatherResponse get(String city) {
        return valueOps.get(CACHE_KEY_PREFIX + city.toLowerCase());
    }

    public void set(String city, WeatherResponse response) {
        valueOps.set(CACHE_KEY_PREFIX + city.toLowerCase(), response, TTL);
    }

    public WeatherResponse getStale(String city) {
        // Redis auto-evicts expired data, so no separate stale logic unless we change eviction policy.
        return get(city);
    }
}
