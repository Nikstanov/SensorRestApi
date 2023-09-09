package com.nikstanov.SensorRestApi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {

    @Setter
    @Getter
    @Size(max = 30, min = 2, message = "Incorrect size")
    @NotEmpty(message = "Name not should be empty")
    private String name;
}
