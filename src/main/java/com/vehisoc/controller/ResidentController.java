package com.vehisoc.controller;

import com.vehisoc.entity.Resident;
import com.vehisoc.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/add")
    public ResponseEntity<String> addResident(@RequestBody Resident resident){
        residentService.createResidentWithVehicle(resident);
        return new ResponseEntity<>("Resident details added successfully", HttpStatus.CREATED);
    }


}
