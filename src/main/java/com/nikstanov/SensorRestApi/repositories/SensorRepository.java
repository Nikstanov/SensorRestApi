package com.nikstanov.SensorRestApi.repositories;

import com.nikstanov.SensorRestApi.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Optional<Sensor> findSensorByName(String name);
}
