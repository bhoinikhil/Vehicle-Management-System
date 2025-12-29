package com.vehicleManagmentSystem.repository;

import com.vehicleManagmentSystem.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Integer > {


    //JPQL query for find by option firstName or lastName or Both.
    @Query(" SELECT r FROM Resident r WHERE (:firstName IS NULL OR r.fName = :firstName) AND (:lastName IS NULL OR r.lName= :lastName)")
    List<Resident> findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}


// (:firstName IS NULL OR r.fName = :firstName) says that
// firstName = user input. and fName = entity field name.
// If firstName is NULL, don’t check first name
//  OR
// If firstName is NOT NULL, match it with r.fName