# 🌦️ Weather API

A Spring Boot application that provides real-time weather data for default city as **Melbourne** using:

- **Primary API:** [WeatherStack](https://weatherstack.com/)
- **Failover API:** [OpenWeatherMap](https://openweathermap.org/api)
- **Optional Caching Layer:** Redis

---

## 🚀 Features

- Fetches current weather for any city but default to Melbourne.
- Automatic failover to OpenWeatherMap if WeatherStack fails.
- Redis-based caching for performance (can be disabled).
- Implemented using Strategy design pattern.
- Circut Breaker Pattern Resilence4j is used.
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
