package com.vehicleManagmentSystem.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String registerationNumber;

    @Column(nullable = false)
    private String vName;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "association_activated_at")
    private LocalDateTime associationActivatedAt;

    @Column(name = "association_deactivated_at")
    private LocalDateTime associationDeactivatedAt;

    @Column(nullable = false)
    private boolean isVehicleActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private Resident resident;




}
