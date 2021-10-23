package com.mintic.api_mintic;

import com.mintic.api_mintic.utils.AppContext;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiMinticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMinticApplication.class, args);
		System.out.println("api corriendo exitosamente...");
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelmapper = new ModelMapper();

		return modelmapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AppContext appContext(){
		return new AppContext();
	}

}
