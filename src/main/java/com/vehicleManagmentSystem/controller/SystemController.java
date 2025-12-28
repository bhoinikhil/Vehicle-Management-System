package com.vehicleManagmentSystem.controller;

import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.entity.Vehical;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SystemController {
    @Autowired
    VehicalManagementService vehicalManagementService;

    // This API save the resident with vehicleList
    @PostMapping("/addNewResident")
    ResponseEntity<Resident> addNewResident(@Valid @RequestBody Resident resident) {
        Resident residentFromDb = vehicalManagementService.addResident(resident);
        return new ResponseEntity<>(residentFromDb, HttpStatus.CREATED);
    }

    //This API send list of resident.
    @GetMapping("/getAllResident" )
    ResponseEntity<List<Resident>> getAllResident(){
        List<Resident> residentList =vehicalManagementService.getAllResidents();
        return new ResponseEntity<>(residentList, HttpStatus.FOUND);
    }

    //This method Find resident from first name and last name.
    @GetMapping("/getByfnamelname")
    ResponseEntity<Resident> getResidentByFnameandLname(String fname,String lname) throws InvalidParameterException {
        if (fname.isBlank() || lname.isBlank()){
            throw new InvalidParameterException("fname and lname cannot be empty");
        }else {
            Resident result = vehicalManagementService.getResidentByName(fname,lname);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }

    }



}
