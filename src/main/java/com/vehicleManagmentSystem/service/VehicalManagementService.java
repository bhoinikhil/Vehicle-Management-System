package com.vehicleManagmentSystem.service;


import com.vehicleManagmentSystem.entity.Resident;

import java.util.List;


public interface VehicalManagementService {
    // This method add resident details with vehicle list.
    Resident addResident(Resident resident);

    // This method give all resident list to the user.
     List<Resident> getAllResidents();

     // This method gives list of Resident on the basis of input.
    List<Resident> getByName(String fname, String lname);
}
