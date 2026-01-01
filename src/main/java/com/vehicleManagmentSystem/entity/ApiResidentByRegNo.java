package com.vehicleManagmentSystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component // use to tell spring load this class this is not entity
public class ApiResidentByRegNo {

    private String firstName;
    private String lastName;
    private String email;
    private long MobileNo;
    private String flatNo;

}
