package com.vehicleManagmentSystem.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String fName;

    @Column(nullable = false)
    private String lName;

    @Column(nullable = false)
    private String flatNo;

    @Column(nullable = false)
    private long mobileNo;

    @Column(nullable = false)
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
