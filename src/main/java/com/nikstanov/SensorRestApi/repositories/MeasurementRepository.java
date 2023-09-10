package com.nikstanov.SensorRestApi.repositories;

import com.nikstanov.SensorRestApi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countByRaining(boolean value);
}
