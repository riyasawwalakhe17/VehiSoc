package com.vehisoc.service;

import com.vehisoc.entity.Resident;

import java.util.List;

public interface ResidentService {

    String createResidentWithVehicle(Resident resident);

    List<Resident> getAllResidents();


}
