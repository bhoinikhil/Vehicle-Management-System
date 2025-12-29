package com.vehicleManagmentSystem.serviceImpl;

import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.entity.exception.UserNotFoundByException;
import com.vehicleManagmentSystem.repository.ResidentRepository;
import com.vehicleManagmentSystem.repository.VehicalRepository;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicalManagementServiceImpl implements VehicalManagementService {
    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    VehicalRepository vehicalRepository;


    // this method save resident.
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

    // this method send the list of resident to user.
    @Override
    public List<Resident> getAllResidents() {
        List<Resident> residentList = residentRepository.findAll();
        return residentList;
    }


    @Override
    public List<Resident> getByName(String fname, String lname) {
        return residentRepository.findByName(fname,lname);
    }

}
