package com.vehicleManagmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleManagementSystemApplication {

	public static void main(String[] args) {
        System.err.println("Starting Vehicle Management System");
		SpringApplication.run(VehicleManagementSystemApplication.class, args);
        System.err.println("Successfully started");
	}

}
