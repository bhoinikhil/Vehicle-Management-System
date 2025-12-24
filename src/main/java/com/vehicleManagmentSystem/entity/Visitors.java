package com.vehicleManagmentSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Visitors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String visitorName;
    private String vehicleName;
    private String vehicleRegisterationNumber;
    private String visitPurpose;

    @Column(name = "timeIn")
    private LocalDateTime timeIn;

    @Column(name = "timeOut")
    private LocalDateTime timeOut;

    private Long phoneNumber;

    private boolean isActiveVisitor;

    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private Resident resident;

}
