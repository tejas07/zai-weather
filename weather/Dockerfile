# Use a lightweight JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/weather-app.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["./wait-for-it.sh","redis","6379","--","java", "-jar", "app.jar"]