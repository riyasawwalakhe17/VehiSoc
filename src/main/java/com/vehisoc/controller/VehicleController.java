package com.vehisoc.controller;

import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Vehicle;
import com.vehisoc.repository.VehicleRepository;
import com.vehisoc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add/{residentId}")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle,
                                             @PathVariable Integer residentId) {
        String response = vehicleService.createVehicle(vehicle, residentId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





}
