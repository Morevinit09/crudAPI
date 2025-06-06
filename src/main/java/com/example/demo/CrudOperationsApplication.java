package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableScheduling
public class CrudOperationsApplication {

	 @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsApplication.class, args);
	}

}
