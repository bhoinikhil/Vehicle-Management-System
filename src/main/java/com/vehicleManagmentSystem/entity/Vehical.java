package com.vehicleManagmentSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Vehical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // id not showing user
    private long id;

    @NotEmpty(message = "Enter RegisterationNumber")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$" , message = "Invalid formate Expected format like MH19AB1234")
    private String registerationNumber;

    @NotEmpty(message = "Please enter Vehical ")
    private String vName;

    @NotEmpty(message = "Please Enter vehical color")
    private String color;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "association_activated_at")
    @JsonIgnore
    @CreatedDate
    private LocalDateTime associationActivatedAt;

    @Column(name = "association_deactivated_at")
    private LocalDateTime associationDeactivatedAt;

    private boolean VehicleActive;

    @ManyToOne()
    @JoinColumn(name = "resident_id")
    @JsonBackReference
    private Resident resident;




}
