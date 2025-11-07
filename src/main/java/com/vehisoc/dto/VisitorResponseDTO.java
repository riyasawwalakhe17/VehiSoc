package com.vehisoc.dto;

import com.vehisoc.entity.VisitorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorResponseDTO {

    private int id;
    private String visitorName;
    private String vehicleName;
    private String vehicleRegNo;
    private String visitPurpose;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private Long phoneNumber;
    private VisitorType visitorType;
    private boolean isActiveVisitor;
    private ResidentDTO resident;

}
