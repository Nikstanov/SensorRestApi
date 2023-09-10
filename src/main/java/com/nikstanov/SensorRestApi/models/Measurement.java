package com.nikstanov.SensorRestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@NoArgsConstructor
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100, message = "Incorrect value")
    @Max(value = 100, message = "Incorrect value")
    @Getter
    @Setter
    private int value;

    @Column(name = "time_at")
    @Getter
    @Setter
    private LocalDateTime time_at;

    @JoinColumn(name = "sensor_owner", referencedColumnName = "sensor_id")
    @ManyToOne
    @Setter
    @Getter
    private Sensor sensor_owner;

    @Column(name = "raining")
    @Getter
    @Setter
    private boolean raining;
}
