package com.zai.weather.config;

import com.zai.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public RedisTemplate<String, WeatherResponse> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, WeatherResponse> template = new RedisTemplate<>();
        try {
            template.setConnectionFactory(lettuceConnectionFactory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        } catch (Exception e) {
            log.info("exception occured while connecting {}", e.getMessage());
        }
        return template;
    }
}
