package com.vehisoc.serviceImpl;

import com.vehisoc.entity.Resident;
import com.vehisoc.repository.ResidentRepository;
import com.vehisoc.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Resident> getAllResidents() {
        List<Resident> residentList = residentRepository.findAll();
        return residentList;
    }

    @Override
    public List<Resident> getResidentByName(String fName, String lName) {

        if ((fName != null && fName.matches(".*\\d.*")) ||
                (lName != null && lName.matches(".*\\d.*"))) {
            throw new IllegalArgumentException("Names cannot contain numeric values");
        }

        List<Resident> residents = residentRepository.findByFirstAndLastName(fName, lName);

        if (residents.isEmpty()) {
            throw new RuntimeException("No resident found with the given details");
        }

        return residents;

    }
}
