package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.ApiVisitorByRegNo;
import com.vehicleManagmentSystem.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitorRepository extends JpaRepository<Visitors , Integer> {

    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegisterationNumber=:regNo")
    Visitors getVisitorByRegNo(@Param("regNo") String regNo);

}
