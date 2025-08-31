package com.vehisoc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String registrationNumber;

    private  String vName;

    private String color;


    @Enumerated(EnumType.STRING)
    private Type vType;

    private LocalDateTime associationActivatedAt;

    private LocalDateTime associationDeactivatedAt;

    private  boolean isVehicleActive;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;



}

