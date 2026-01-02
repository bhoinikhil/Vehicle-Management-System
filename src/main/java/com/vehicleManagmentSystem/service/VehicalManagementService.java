package com.vehicleManagmentSystem.service;


import com.vehicleManagmentSystem.entity.*;
import jakarta.transaction.Transactional;
import java.util.List;


public interface VehicalManagementService {
    // This method add resident details with vehicle list.
    Resident addResident(Resident resident);

    // This method give all resident list to the user.
     List<Resident> getAllResidents();

     // This method gives list of Resident on the basis of input.
    List<Resident> getByName(String fname, String lname);

    //This method add vehical in resident.
    @Transactional
    Vehical addVehicle(Vehical vehical, String email);

    //This method give details by using registrationNumber.
    ApiResidentByRegNo getResidentByRegistrationNumber(String registrationNumber);

    //This method add visitor.
    Visitors addVisitor(Visitors visitors, String email);

    ApiVisitorByRegNo getVisitorByRegistrationNumber(String registrationNumber);
    String updateVisitorByRegistrationNumber(String registrationNumber);

    List<Visitors> getListOfActiveVisitors(List<String> visitorTypes);


}
