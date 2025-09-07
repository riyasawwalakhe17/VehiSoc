package com.vehisoc.controller;

import com.vehisoc.entity.Resident;
import com.vehisoc.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllData")
    public ResponseEntity<List<Resident>> getAllResident(){
        List<Resident> residentList = residentService.getAllResidents();
        return new ResponseEntity<>(residentList,HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchResident(@RequestParam(required = false) String fName,
                                            @RequestParam(required = false) String lName) {
        try {
            List<Resident> residents = residentService.getResidentByName(fName, lName);
            return ResponseEntity.ok(residents);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
