package com.vehicleManagmentSystem.controller;

import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.entity.Vehical;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SystemController {
    @Autowired
    VehicalManagementService vehicalManagementService;

    @PostMapping("/addNewResident")
    ResponseEntity<Resident> addNewResident(@Valid @RequestBody Resident resident) {
        Resident residentFromDb = vehicalManagementService.addResident(resident);
        return new ResponseEntity<>(residentFromDb, HttpStatus.CREATED);
    }



}
