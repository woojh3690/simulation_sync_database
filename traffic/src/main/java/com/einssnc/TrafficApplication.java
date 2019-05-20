package com.einssnc;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.einssnc.dao.NationWideSpeedDao;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.einssnc"})
public class TrafficApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(TrafficApplication.class, args);
		
	}

}
