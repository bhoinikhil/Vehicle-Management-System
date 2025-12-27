package com.vehicleManagmentSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.lang.model.type.UnionType;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not showing user
    private int id;

    @NotEmpty(message = "Please enter Name !!")
    private String fName;

    private String lName;

    @NotEmpty(message = "Please enter flatNo")
    private String flatNo;

    @NotNull(message = "Please enter MobileNo.")
    private long mobileNo;

    @NotEmpty(message = "Email Not be empty")
    @Email(message = "Invalid Emali")
    private String email;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

    @JsonManagedReference
    @OneToMany(mappedBy = "resident",cascade = CascadeType.ALL)
    private List<Vehical> vehicalList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<Visitors> visitorsList = new ArrayList<>();

//    resident(parent) -vehicle(child) one to many ->
//        resident own the multiple vehicles.
//        vehicle is the owining side because it contains the foreign key of resident

//    Resident(parent)-Visitor(Child) one to many ->
//        visitor is owining side.

}
