package com.nikstanov.SensorRestApi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class MeasurementDTO {

    //@NotEmpty(message = "Empty value")
    @Min(value = -100, message = "Incorrect value")
    @Max(value = 100, message = "Incorrect value")
    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    //@NotEmpty(message = "Add raining info")
    private boolean raining;

    @Getter
    @Setter
    //@NotEmpty(message = "Add owner name!")
    private SensorDTO sensor;
}
