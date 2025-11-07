package com.vehisoc.serviceImpl;

import com.vehisoc.dto.ResidentDTO;
import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Vehicle;
import com.vehisoc.mapper.ResidentMapper;
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
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty");
        }

        if (vehicle.getRegistrationNumber().trim().length() != 10) {
            throw new IllegalArgumentException("Invalid registration number. It must be exactly 10 characters.");
        }

        Optional<Resident> residentOpt = residentRepository.findById(residentId);
        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Resident with ID " + residentId + " not found. Vehicle cannot be created.");
        }

        Resident resident = residentOpt.get();
        vehicle.setResident(resident);
        vehicleRepository.save(vehicle);

        return "Vehicle created successfully for Resident ID: " + residentId;
    }


    @Override
    public ResidentDTO getResidentByRegistrationNumber(String regNo) {
        if (regNo == null || regNo.trim().isEmpty() || regNo.trim().length() != 10) {
            throw new IllegalArgumentException("Invalid registration number. It must be exactly 10 characters.");
        }

        Resident resident = vehicleRepository.findResidentByRegistrationNumber(regNo.trim())
                .orElseThrow(() -> new RuntimeException(
                        "No vehicle found with registration number: " + regNo));

        // ✅ Convert Entity → DTO
        return ResidentMapper.toDTO(resident);
    }
}
