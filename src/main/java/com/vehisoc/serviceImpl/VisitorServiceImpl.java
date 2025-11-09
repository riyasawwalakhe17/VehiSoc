package com.vehisoc.serviceImpl;

import com.vehisoc.dto.VisitorResponseDTO;
import com.vehisoc.entity.Resident;
import com.vehisoc.entity.VisitorType;
import com.vehisoc.entity.Visitors;
import com.vehisoc.mapper.VisitorMapper;
import com.vehisoc.repository.ResidentRepository;
import com.vehisoc.repository.VisitorRepository;
import com.vehisoc.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public String addVisitor(Visitors visitor, Integer residentId) {


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


        Optional<Resident> residentOpt = residentRepository.findById(residentId);
        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Resident with ID " + residentId + " not found");
        }

        visitor.setResident(residentOpt.get());
        visitor.setActiveVisitor(true);

        visitorRepository.save(visitor);
        return "Visitor added successfully for resident ID: " + residentId;
    }

    @Override
    public VisitorResponseDTO getVisitorByRegNo(String regNo) {

        if (regNo == null || regNo.trim().length() != 10) {
            throw new IllegalArgumentException("Invalid registration number. It must be exactly 10 characters.");
        }

        List<Visitors> visitors = visitorRepository.findVisitorsByRegNoOrdered(regNo.trim());

        if (visitors.isEmpty()) {
            throw new RuntimeException("No visitor found with registration number: " + regNo);
        }


        Visitors latestVisitor = visitors.get(0);

        return VisitorMapper.toDTO(latestVisitor);
    }

    @Override
    public String updateExitTime(String vehicleRegNo) {
        List<Visitors> visitors = visitorRepository.findVisitorsByRegNoOrdered(vehicleRegNo.trim());

        if (visitors.isEmpty()) {
            throw new RuntimeException("No active visitor found with vehicleRegNo: " + vehicleRegNo);
        }


        Visitors visitor = visitors.get(0);

        if (!visitor.isActiveVisitor()) {
            return "Visitor already checked out.";
        }

        visitor.setTimeOut(LocalDateTime.now());
        visitor.setActiveVisitor(false);


        Duration duration = Duration.between(visitor.getTimeIn(), visitor.getTimeOut());
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        String formattedDuration = String.format("%02d:%02d", hours, minutes);
        visitor.setVisitDuration(formattedDuration);

        visitorRepository.save(visitor);

        return "Exit time updated successfully. Duration: " + formattedDuration;
    }


    @Override
    public List<VisitorResponseDTO> getActiveVisitors(List<VisitorType> types) {
        List<Visitors> visitors;

        if (types == null || types.isEmpty()) {
            visitors = visitorRepository.findByIsActiveVisitorTrue();
        } else {

            visitors = visitorRepository.findByIsActiveVisitorTrueAndVisitorTypeIn(types);
        }

        return visitors.stream()
                .map(VisitorMapper::toDTO)
                .toList();

    }


}

