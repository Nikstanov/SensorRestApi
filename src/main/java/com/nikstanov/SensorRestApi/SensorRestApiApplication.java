package com.nikstanov.SensorRestApi;

import com.nikstanov.SensorRestApi.dto.MeasurementDTO;
import com.nikstanov.SensorRestApi.dto.SensorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;


@SpringBootApplication
public class SensorRestApiApplication {
	static Random random = new Random();

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		SensorDTO sensorDTO = new SensorDTO("sensor2");
		String registrationURL
				= "http://localhost:8080/sensors/registration";
		restTemplate.postForEntity(registrationURL, sensorDTO, ResponseEntity.class);

		String addURL
				= "http://localhost:8080/measurements/add";
		for(int i = 0; i < 100; i++){
			restTemplate.postForEntity(addURL, new MeasurementDTO(random.nextInt(), true, sensorDTO), ResponseEntity.class);
		}

		String getURL
				= "http://localhost:8080/measurements";
		List<MeasurementDTO> results = restTemplate.getForEntity(getURL, List.class).getBody();
		for(MeasurementDTO measurementDTO : results){
			System.out.println(measurementDTO.toString());
		}
		//SpringApplication.run(SensorRestApiApplication.class, args);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
