package com.vehicleManagmentSystem.serviceImpl;

import com.vehicleManagmentSystem.entity.*;
import com.vehicleManagmentSystem.entity.exception.UserNotFoundByException;
import com.vehicleManagmentSystem.repository.ResidentRepository;
import com.vehicleManagmentSystem.repository.VehicalRepository;
import com.vehicleManagmentSystem.repository.VisitorRepository;
import com.vehicleManagmentSystem.service.VehicalManagementService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicalManagementServiceImpl implements VehicalManagementService {
    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    VehicalRepository vehicalRepository;
    @Autowired
    VisitorRepository visitorRepository;

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
                if(residentList.isEmpty()){
                    throw new UserNotFoundByException("User Not Found");
                }
        return residentList;
    }

    @Override
    public List<Resident> getByName(String fname, String lname) {
        return residentRepository.findByName(fname,lname);
    }

    @Transactional
    @Override
    public Vehical addVehicle(Vehical vehical, String email) {
        if (vehical== null){
            throw new InvalidParameterException("Empty Vehicle is not allowed");
        }else if (email.isEmpty()){
            throw new InvalidParameterException("Empty Email is not allowed");
        }
        Resident resident = residentRepository.findByEmail(email);
        if (vehical.isVehicleActive()){
            vehical.setAssociationActivatedAt(LocalDateTime.now());
        }
        vehical.setResident(resident);
        resident.getVehicalList().add(vehical);
        Resident residentFromDb =  residentRepository.save(resident);
        Vehical updatedVehical = residentFromDb.getVehicalList().get(residentFromDb.getVehicalList().size()-1);
        return updatedVehical;
    }

    @Autowired
    ApiResidentByRegNo residentByReg;

    @Override
    public ApiResidentByRegNo getResidentByRegistrationNumber(String registrationNumber) {
        Resident resident = vehicalRepository.findByRegistrationNumber(registrationNumber);
        if (resident == null){
            throw new UserNotFoundByException("User Not Found with this Registration Number: " + registrationNumber);
        }
        //Adding resident data to object and send this object as response to user.
        residentByReg.setFirstName(resident.getFName());
        residentByReg.setLastName(resident.getLName());
        residentByReg.setEmail(resident.getEmail());
        residentByReg.setMobileNo(resident.getMobileNo());
        residentByReg.setFlatNo(resident.getFlatNo());


        return residentByReg;
    }

    @Override
    public Visitors addVisitor(Visitors visitor, String email) {
        Resident resident = residentRepository.findByEmail(email);
        if (resident == null){
            throw new UserNotFoundByException("User Not Found with this Email: " + email);
        }
        visitor.setActiveVisitor(true);
        visitor.setTimeIn(LocalDateTime.now());
        resident.addVistior(visitor);
        residentRepository.save(resident);
        return visitor;
    }

    @Autowired
    ApiVisitorByRegNo visitorApi;
    @Override
    public ApiVisitorByRegNo getVisitorByRegistrationNumber(String registrationNumber) {
       Visitors visitor = visitorRepository.getVisitorByRegNo(registrationNumber);
       if (visitor == null){
           throw new UserNotFoundByException("User Not Found with this Registration Number: " + registrationNumber);
       }
        //Adding Data to custom object
        visitorApi.setVisitorName(visitor.getVisitorName());
        visitorApi.setVisitorMobileNo(visitor.getPhoneNumber());
        visitorApi.setVisitPurpose(visitor.getVisitPurpose());
        visitorApi.setVehicalName(visitor.getVehicleName());
        visitorApi.setTimeIn(visitor.getTimeIn());

        visitorApi.setResidentFirstName(visitor.getResident().getFName());
        visitorApi.setResidentLastName(visitor.getResident().getLName());
        visitorApi.setResidentEmail(visitor.getResident().getEmail());
        visitorApi.setResidentMobileNo(visitor.getPhoneNumber());
        visitorApi.setResidentFlatNo(visitor.getResident().getFlatNo());

        return visitorApi;
    }
}
