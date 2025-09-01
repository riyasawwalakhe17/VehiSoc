package com.vehisoc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Registration number is mandatory")
    private String registrationNumber;

    @NotBlank(message = "Vehicle name is mandatory")
    @JsonProperty("vname")
    private  String vName;

    @NotBlank(message = "Color is mandatory")
    private String color;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type is mandatory")
    @JsonProperty("vtype")
    private Type vType;

    private LocalDateTime associationActivatedAt;

    private LocalDateTime associationDeactivatedAt;



    private  boolean isVehicleActive;


    @ManyToOne
    @JoinColumn(name = "resident_id" , nullable = false)
    private Resident resident;

    @PrePersist
    public void onCreate() {
        this.associationActivatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        if (this.associationDeactivatedAt == null) {
            this.associationDeactivatedAt = LocalDateTime.now();
        }
    }


}

