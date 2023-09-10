package com.nikstanov.SensorRestApi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nikstanov.SensorRestApi.utills.JsonBooleanConverter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor.getName() +
                '}';
    }

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
