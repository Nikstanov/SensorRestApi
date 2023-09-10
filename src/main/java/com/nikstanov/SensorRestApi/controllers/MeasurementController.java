package com.nikstanov.SensorRestApi.controllers;

import com.nikstanov.SensorRestApi.dto.MeasurementDTO;
import com.nikstanov.SensorRestApi.dto.SensorDTO;
import com.nikstanov.SensorRestApi.models.Measurement;
import com.nikstanov.SensorRestApi.services.MeasurementService;
import com.nikstanov.SensorRestApi.services.SensorService;
import com.nikstanov.SensorRestApi.utills.ErrorResponse;
import com.nikstanov.SensorRestApi.utills.IncorrectMeasurementException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/measurements")
@RestController
public class MeasurementController {

    private final SensorService sensorService;
    private final MeasurementService measurementService;
    private final ModelMapper mapper;

    public MeasurementController(SensorService sensorService, MeasurementService measurementService, ModelMapper mapper) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(measurementDTO.getSensor() == null){
            throw new IncorrectMeasurementException("Empty owner");
        }
        if(!sensorService.existByName(measurementDTO.getSensor().getName())){
            throw new IncorrectMeasurementException("Unknown owner");
        }
        if(bindingResult.hasErrors()){
            StringBuilder str = new StringBuilder();
            for(FieldError error : bindingResult.getFieldErrors()){
                str.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new IncorrectMeasurementException(str.toString());
        }
        measurementService.addNewMeasurement(mapper.map(measurementDTO, Measurement.class), measurementDTO.getSensor().getName());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<MeasurementDTO> getAll(){
        return measurementService.getAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainingDaysCount(){
        return measurementService.getRainingCount();
    }

    private MeasurementDTO convertToDTO(Measurement measurement){
        MeasurementDTO measurementDTO = mapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(mapper.map(measurement.getSensor_owner(), SensorDTO.class));
        return measurementDTO;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIncorrectMeasurementException(IncorrectMeasurementException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
