package com.vehicleManagmentSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;



@Entity
@Getter
@Setter
@ToString
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String fName;

    @NotEmpty
    private String lName;

    @NotNull
    private String flatNo;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private long mobileNo;

    @Email(message = "Invalid Emali")
    private String email;

    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private ArrayList<Vehical> vehicalList;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private ArrayList<Visitors> visitorList;

//    resident-vehicle one to many ->
//        resident own the multiple vehicles.
//        vehicle is the owining side because it contains the foreign key of resident

//    Resident-Visitor one to many ->
//        visitor is owining side.

}
