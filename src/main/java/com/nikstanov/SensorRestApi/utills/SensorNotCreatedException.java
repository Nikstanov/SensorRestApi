package com.nikstanov.SensorRestApi.utills;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String message) {
        super(message);
    }
}
