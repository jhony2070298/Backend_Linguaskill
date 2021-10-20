package com.mintic.api_mintic;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiMinticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMinticApplication.class, args);
		System.out.println("api corriendo exitosamente...");
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();

		return mapper;
	}

}
