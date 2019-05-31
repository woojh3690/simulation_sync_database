package com.einssnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.einssnc"})
public class TrafficApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(TrafficApplication.class, args);
		
	}

}
