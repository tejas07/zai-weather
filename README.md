# 🌦️ Weather API

A Spring Boot application that provides real-time weather data for default city as **Melbourne** using:

- **Primary API:** [WeatherStack](https://weatherstack.com/)
- **Failover API:** [OpenWeatherMap](https://openweathermap.org/api)
- **Optional Caching Layer:** Redis

---

## 🚀 Features

- Fetches current weather for any city but defaults to Melbourne.
- Automatic failover to OpenWeatherMap if WeatherStack fails.
- Redis-based caching for performance (**currently commented out** when using Docker Compose).
- Implemented using Strategy design pattern.
- Circuit Breaker pattern using Resilience4j.
- Simple REST endpoint:

---

## 🧰 Tech Stack

- Java 17  
- Spring Boot 3.4.4
- Spring Web, Spring Data Redis  
- SLF4J + Logback  
- Maven  
- Redis (optional)

---

## ⚙️ Configuration

### API Keys

Add your WeatherStack and OpenWeatherMap API keys to `application.properties` or environment variables:

```properties
weather.api.key=YOUR_WEATHERSTACK_KEY
openweather.api.key=YOUR_OPENWEATHERMAP_KEY

# Enable or disable Redis caching
app.redis.enabled=true
```

---

## ⚠️ Note on Docker + Redis

> **Important:** Redis caching is implemented but currently disabled when using `docker-compose` due to connection issues with Redis startup timing.
>
> - Redis-related code is commented out temporarily.
> - The app still works with live weather data directly from APIs.
> - To re-enable Redis, ensure it starts fully before the app (e.g. use `wait-for-it.sh` or Spring Retry).
