package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Integer > {

    @Query("SELECT r FROM Resident r WHERE r.fName=:fname AND r.lName=:lname")
    public Resident getResidentByFnameAndLname(@Param("fname") String fname, @Param("lname") String lname );
}
