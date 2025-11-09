package com.vehisoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
public class Visitors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Visitor name is mandatory")
    private String visitorName;

    @NotBlank(message = "Vehicle name is mandatory")
    private String vehicleName;

    @NotBlank(message = "Vehicle registration number is mandatory")
    private String vehicleRegNo;

    @NotBlank(message = "Visit purpose is mandatory")
    private String visitPurpose;

    @NotNull(message = "Time-In is required")
    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    @NotNull(message = "Phone number is required")
    private Long phoneNumber;

    private boolean isActiveVisitor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Visitor type is required")
    private VisitorType visitorType;

    private String visitDuration;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    @JsonIgnore
    private Resident resident;

}
