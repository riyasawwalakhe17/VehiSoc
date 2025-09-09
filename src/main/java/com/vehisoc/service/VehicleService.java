package com.vehisoc.service;

import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Vehicle;

public interface VehicleService {

    String createVehicle(Vehicle vehicle, Integer residentId);


}
