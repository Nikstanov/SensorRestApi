package com.nikstanov.SensorRestApi.services;

import com.nikstanov.SensorRestApi.models.Sensor;
import com.nikstanov.SensorRestApi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        makeTimestamp(sensor);
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getByName(String name){
        return sensorRepository.findSensorByName(name);
    }

    public boolean existByName(String name){
        return sensorRepository.findSensorByName(name).isPresent();
    }

    public List<Sensor> getAll()
    {
        return sensorRepository.findAll();
    }
    private void makeTimestamp(Sensor sensor){
        sensor.setCreated_at(LocalDateTime.now());
    }
}
