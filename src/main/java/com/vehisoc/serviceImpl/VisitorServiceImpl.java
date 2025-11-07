package com.vehisoc.serviceImpl;

import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Visitors;
import com.vehisoc.repository.ResidentRepository;
import com.vehisoc.repository.VisitorRepository;
import com.vehisoc.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public String addVisitor(Visitors visitor, Integer residentId) {

        // 🔍 Validate mandatory fields
        if (visitor.getVisitorName() == null || visitor.getVisitorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Visitor name is mandatory");
        }

        if (visitor.getVehicleName() == null || visitor.getVehicleName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle name is mandatory");
        }

        if (visitor.getVehicleRegNo() == null || visitor.getVehicleRegNo().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number is mandatory");
        }

        if (visitor.getVisitPurpose() == null || visitor.getVisitPurpose().trim().isEmpty()) {
            throw new IllegalArgumentException("Visit purpose is mandatory");
        }

        if (visitor.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Phone number is required");
        }

        if (visitor.getVisitorType() == null) {
            throw new IllegalArgumentException("Visitor type is required");
        }

        if (visitor.getTimeIn() == null) {
            throw new IllegalArgumentException("Time-In is required");
        }

        // 🔗 Validate and map resident
        Optional<Resident> residentOpt = residentRepository.findById(residentId);
        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Resident with ID " + residentId + " not found");
        }

        visitor.setResident(residentOpt.get());
        visitor.setActiveVisitor(true);

        visitorRepository.save(visitor);
        return "Visitor added successfully for resident ID: " + residentId;
    }
}
