package com.vehicleManagmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehicleManagementSystemApplication {

	public static void main(String[] args) {
        System.err.println("Starting Vehicle Management System");
		SpringApplication.run(VehicleManagementSystemApplication.class, args);
        System.err.println("Successfully started");
	}

}
