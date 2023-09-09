package com.nikstanov.SensorRestApi.utills;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
