package com.vehicleManagmentSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Vehical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Enter RegisterationNumber")
    private String registerationNumber;

    @NotEmpty
    private String vName;

    @NotEmpty
    private String color;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "association_activated_at")
    private LocalDateTime associationActivatedAt;

    @Column(name = "association_deactivated_at")
    private LocalDateTime associationDeactivatedAt;

    @NotEmpty
    private boolean isVehicleActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private Resident resident;




}
