package com.vehisoc.serviceImpl;

import com.vehisoc.entity.Resident;
import com.vehisoc.repository.ResidentRepository;
import com.vehisoc.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public String createResidentWithVehicle(Resident resident) {
        if (resident.getVehicleList() != null) {
            resident.getVehicleList().forEach(vehicle -> vehicle.setResident(resident));
        }
        residentRepository.save(resident);
        return "Resident details added successfully";
    }
}
