package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.Resident;
import com.vehicleManagmentSystem.entity.Vehical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicalRepository extends JpaRepository<Vehical,Integer> {

    @Query("SELECT v.resident FROM Vehical v WHERE v.registerationNumber=:registrationNumber")
    Resident findByRegistrationNumber(String registrationNumber);
}
