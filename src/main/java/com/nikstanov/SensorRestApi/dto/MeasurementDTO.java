package com.nikstanov.SensorRestApi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nikstanov.SensorRestApi.utills.JsonBooleanConverter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

public class MeasurementDTO {

    @Min(value = -100, message = "Incorrect value")
    @Max(value = 100, message = "Incorrect value")
    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    @JsonDeserialize(using = JsonBooleanConverter.class)
    private boolean raining;

    @Getter
    @Setter
    private SensorDTO sensor;
}
