package com.vehisoc.controller;

import com.vehisoc.dto.VisitorResponseDTO;
import com.vehisoc.entity.Visitors;
import com.vehisoc.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping("/add/{residentId}")
    public ResponseEntity<String> addVisitor(@RequestBody Visitors visitor,
                                             @PathVariable Integer residentId) {
        String response = visitorService.addVisitor(visitor, residentId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getByRegNo")
    public ResponseEntity<?> getVisitorByRegistrationNumber(@RequestParam String regNo) {
        VisitorResponseDTO response = visitorService.getVisitorByRegNo(regNo);
        return ResponseEntity.ok(response);
    }
}
