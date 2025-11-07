package com.vehisoc.controller;

import com.vehisoc.dto.ResidentDTO;
import com.vehisoc.entity.Vehicle;
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

    @GetMapping("/getResidentByRegNo")
    public ResponseEntity<?> getResidentByRegistrationNumber(@RequestParam String regNo) {
        try {
            ResidentDTO residentDTO = vehicleService.getResidentByRegistrationNumber(regNo);
            return ResponseEntity.ok(residentDTO); // ✅ Returning DTO, not entity
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
