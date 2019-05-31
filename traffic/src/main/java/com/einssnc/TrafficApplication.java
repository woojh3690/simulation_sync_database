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
		
		String profile = System.getProperty("spring.profiles.active");
        if(profile == null) {
            System.setProperty("spring.profiles.active", "client");
        }
        
		SpringApplication.run(TrafficApplication.class, args);
		
	}

}
