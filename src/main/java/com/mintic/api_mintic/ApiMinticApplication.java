package com.mintic.api_mintic;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiMinticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMinticApplication.class, args);
		System.out.println("api corriendo exitosamenteee...");
	}

	@Bean
	public ModelMapper modelMapper(){

		ModelMapper mapper = new ModelMapper();

		return mapper;
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	} 

}
