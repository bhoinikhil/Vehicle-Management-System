package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.Vehical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicalRepository extends JpaRepository<Vehical,Integer> {


}
