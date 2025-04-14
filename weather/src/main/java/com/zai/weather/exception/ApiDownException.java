package com.zai.weather.exception;

public class ApiDownException extends RuntimeException{
    public ApiDownException(String message) {
        super(message);
    }

    public ApiDownException(String message, Throwable cause) {
        super(message, cause);
    }
}
