package com.vehicleManagmentSystem.controller;
import com.vehicleManagmentSystem.entity.*;
import com.vehicleManagmentSystem.entity.exception.UserNotFoundByException;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;
import java.util.List;

@RestController
@Validated
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

    @PutMapping("/addVehicle")
    ResponseEntity<Vehical> addVehicle(@Valid @RequestBody Vehical vehical, @RequestParam @NotEmpty(message = "Email Id is required") @Email(message = "Invalid email format") String email){
        Vehical vehicalFromDb = vehicalManagementService.addVehicle(vehical,email);
        return new ResponseEntity<>(vehicalFromDb, HttpStatus.CREATED);
    }

    @GetMapping("/getResidentByRegNo")
    ResponseEntity<ApiResidentByRegNo> getResidentByRegistrationNumber
            (@RequestParam
             @NotEmpty(message = "Registration number is required")
             @Pattern(regexp= "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$",message = "Invalid registration number format (e.g. MH19CB1234)")
             String registrationNumber)
    {

       ApiResidentByRegNo residentFromDb = vehicalManagementService.getResidentByRegistrationNumber(registrationNumber);
       return new ResponseEntity<>(residentFromDb, HttpStatus.FOUND);
    }

    @Transactional
    @PostMapping("/addVisitor")
    ResponseEntity<Visitors> addVisitor(@RequestBody Visitors visitor, @RequestParam @NotEmpty(message = "Email Id is required") @Email(message = "Invalid email format") String email){
         Visitors visitorFromDb = vehicalManagementService.addVisitor(visitor,email);
         return new ResponseEntity<>(visitorFromDb, HttpStatus.CREATED);
    }

    @GetMapping("/getVisitorByRegNo")
    ResponseEntity<ApiVisitorByRegNo> getVisitorByRegistrationName
            (@RequestParam
             @NotEmpty(message = "Registration number is required")
             @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$",message = "Invalid formate Expected format like MH19AB1234")
             String registrationNumber){
        ApiVisitorByRegNo visitorDetails = vehicalManagementService.getVisitorByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(visitorDetails, HttpStatus.FOUND);
    }

    @PutMapping("/updateVisitorOutTime")
    ResponseEntity<String> updateVisitorOutTime
            (@RequestParam
             @NotEmpty(message = "Registration number is required")
             @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$",message = "Invalid formate Expected format like MH19AB1234")
             String registrationNumber
            )
    {
         String msg = vehicalManagementService.updateVisitorByRegistrationNumber(registrationNumber);
         return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/getAllActiveVisitors")
    ResponseEntity<List<Visitors>> getAllActiveVisitors(@RequestParam List<String> listOfVisitorType){
        List<Visitors> AllActiveVisitors =vehicalManagementService.getListOfActiveVisitors(listOfVisitorType);
        return new ResponseEntity<>(AllActiveVisitors,HttpStatus.OK);
    }


}
