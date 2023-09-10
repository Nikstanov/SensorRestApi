package com.nikstanov.SensorRestApi.services;

import com.nikstanov.SensorRestApi.models.Measurement;
import com.nikstanov.SensorRestApi.repositories.MeasurementRepository;
import com.nikstanov.SensorRestApi.utills.IncorrectMeasurementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void addNewMeasurement(Measurement measurement, String owner){
        try{
            measurement.setSensor_owner(sensorService.getByName(owner).orElseThrow());
        }
        catch (NoSuchElementException e){
            throw new IncorrectMeasurementException("Incorrect owner");
        }

        makeTimestamp(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAll(){
        return measurementRepository.findAll();
    }

    public int getRainingCount(){
        return measurementRepository.countByRaining(true);
    }

    private void makeTimestamp(Measurement measurement){
        measurement.setTime_at(LocalDateTime.now());
    }

}
