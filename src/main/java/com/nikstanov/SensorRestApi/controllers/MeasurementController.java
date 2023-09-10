package com.nikstanov.SensorRestApi.controllers;

import com.nikstanov.SensorRestApi.dto.MeasurementDTO;
import com.nikstanov.SensorRestApi.services.SensorService;
import com.nikstanov.SensorRestApi.utills.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/measurements")
@RestController
public class MeasurementController {

    private final SensorService sensorService;

    public MeasurementController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(!sensorService.existByName(measurementDTO.getSensor().getName())){
            bindingResult.addError(new FieldError("owner","","owner hasn't registered"));
        }
        if(bindingResult.hasErrors()){
            StringBuilder str = new StringBuilder();
            for(FieldError error : bindingResult.getFieldErrors()){
                str.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            //throw new SensorNotCreatedException(str.toString());
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
