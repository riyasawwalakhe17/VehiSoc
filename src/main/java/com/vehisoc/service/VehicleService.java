package com.vehisoc.service;

import com.vehisoc.dto.ResidentDTO;
import com.vehisoc.entity.Vehicle;

public interface VehicleService {

    String createVehicle(Vehicle vehicle, Integer residentId);
    ResidentDTO getResidentByRegistrationNumber(String regNo);

}
