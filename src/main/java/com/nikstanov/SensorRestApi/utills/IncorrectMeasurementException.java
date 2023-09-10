package com.nikstanov.SensorRestApi.utills;

public class IncorrectMeasurementException extends RuntimeException{
    public IncorrectMeasurementException(String message) {
        super(message);
    }
}
