package com.nikstanov.SensorRestApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SensorRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorRestApiApplication.class, args);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
