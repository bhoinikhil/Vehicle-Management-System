package com.vehicleManagmentSystem.serviceImpl;

import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.repository.ResidentRepository;
import com.vehicleManagmentSystem.repository.VehicalRepository;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicalManagementServiceImpl implements VehicalManagementService {
    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    VehicalRepository vehicalRepository;


    @Override
    public Resident addResident(Resident resident) {
        // adding FK inside Vehicle table
        //manully linking the FK
        if(resident.getVehicalList()!=null){  // checking if vehiclelist is not a null
            resident.getVehicalList().forEach(vehical -> {  // iteration of whole vehicle list
                vehical.setResident(resident);   // Linking each vehical to thier owner
            });
        }
        resident.getVehicalList().forEach(vehical -> {
        // this method check user send isvehicalActive if active then set localtime and date in db.
            if(vehical.isVehicleActive()){
                vehical.setAssociationActivatedAt(LocalDateTime.now());
            }
        });
        Resident residentFromDb= residentRepository.save(resident);  // save resident
        return residentFromDb;
    }
}
