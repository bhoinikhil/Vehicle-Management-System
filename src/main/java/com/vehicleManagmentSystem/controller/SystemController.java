package com.vehicleManagmentSystem.controller;
import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.entity.exception.UserNotFoundByException;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;
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

    //This API send list of All resident.
    @GetMapping("/getAllResident" )
    ResponseEntity<List<Resident>> getAllResident(){
        List<Resident> residentList =vehicalManagementService.getAllResidents();
        return new ResponseEntity<>(residentList, HttpStatus.FOUND);
    }


    // This API send Resident list to user find by options like firstname ,lastname and both.
    @GetMapping("/getByfnamelname")
    ResponseEntity<List<Resident>> getResidentByFnameandLname(@RequestParam(required = false) String fname,@RequestParam(required = false) String lname){
       if (( fname == null || fname.trim().isEmpty()) && (lname == null || lname.trim().isEmpty())){
           //This condition validate the user input like notnull,empty, etc.
           throw new InvalidParameterException("First Name or Last Name Required !!");
       }
           List<Resident> listUser = vehicalManagementService.getByName(fname!=null?fname.trim():fname, lname!=null?lname.trim():lname);
           if (listUser.isEmpty()){ // this condition check data from database is null or empty
               throw new UserNotFoundByException("User not found With the name "+fname +" "+lname);
               //if data is null or empty the throw exception.
           }
       return new ResponseEntity<>(listUser, HttpStatus.FOUND);
    }


}
