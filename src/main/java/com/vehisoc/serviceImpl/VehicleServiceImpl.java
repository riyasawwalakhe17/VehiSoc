package com.vehisoc.serviceImpl;

import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Vehicle;
import com.vehisoc.repository.ResidentRepository;
import com.vehisoc.repository.VehicleRepository;
import com.vehisoc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public String createVehicle(Vehicle vehicle, Integer residentId) {
        //Validation: registration number should not be empty
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty");
        }

        //Check if resident exists
        Optional<Resident> residentOpt = residentRepository.findById(residentId);
        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Resident with ID " + residentId + " not found. Vehicle cannot be created.");
        }

        // Set resident for vehicle
        Resident resident = residentOpt.get();
        vehicle.setResident(resident);

        vehicleRepository.save(vehicle);
        return "Vehicle created successfully for Resident ID: " + residentId;

    }


}
