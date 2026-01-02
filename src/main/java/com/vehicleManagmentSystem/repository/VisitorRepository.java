package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.VisitorType;
import com.vehicleManagmentSystem.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitors , Integer> {

    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegisterationNumber=:regNo")
    Visitors getVisitorByRegNo(@Param("regNo") String regNo);

    @Query("SELECT v FROM Visitors v Where v.isActiveVisitor=true AND v.visitorType IN:visitorType ")
    List<Visitors> getListOfActiveVisitors(@Param("visitorType") List<String> visitorType);

}
