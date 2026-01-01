package com.vehicleManagmentSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Component
public class ApiVisitorByRegNo {

    //Resident Details
    private String residentFirstName;
    private String residentLastName;
    private String residentEmail;
    private long residentMobileNo;
    private String residentFlatNo;

    //Visitor Details
    private String visitorName;
    private String vehicalName;
    private long visitorMobileNo;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String visitPurpose;



}
