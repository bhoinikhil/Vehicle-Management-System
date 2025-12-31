package com.vehicleManagmentSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @JsonIgnore
    private int id;

    @NotEmpty(message = "Visitor name is required")
    private String visitorName;

    @NotEmpty(message = "Vehical name is requierd")
    private String vehicleName;

    @NotBlank(message = "Vehicle number is required")
    @Pattern(
            regexp = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$",
            message = "Invalid vehicle number format (e.g., MH19CB1234)"
    )
    private String vehicleRegisterationNumber;

    private String visitPurpose;

    @Column(name = "timeIn")
    private LocalDateTime timeIn;

    @Column(name = "timeOut")
    private LocalDateTime timeOut;

    @NotNull(message = "Mobile number is required ")
    private long phoneNumber;

    private boolean isActiveVisitor;

    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private Resident resident;


}
