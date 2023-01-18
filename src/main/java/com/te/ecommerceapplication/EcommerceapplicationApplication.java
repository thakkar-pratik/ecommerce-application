package com.te.ecommerceapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceapplicationApplication.class, args);	
	}
	
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
