package com.nikstanov.SensorRestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensors")
@NoArgsConstructor
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int sensor_id;

    public Sensor(String name, LocalDateTime created_at) {
        this.name = name;
        this.created_at = created_at;
    }

    @Column(name = "sensor_name")
    @Size(max = 30, min = 2, message = "Incorrect size")
    @NotEmpty(message = "Name not should be empty")
    @Getter
    @Setter
    private String name;

    @Column(name = "created_at")
    @Getter
    @Setter
    private LocalDateTime created_at;
}
