package com.vehisoc.mapper;

import com.vehisoc.dto.ResidentDTO;
import com.vehisoc.dto.VisitorResponseDTO;
import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Visitors;

public class VisitorMapper {

    public static VisitorResponseDTO toDTO(Visitors visitor) {
        VisitorResponseDTO dto = new VisitorResponseDTO();
        dto.setId(visitor.getId());
        dto.setVisitorName(visitor.getVisitorName());
        dto.setVehicleName(visitor.getVehicleName());
        dto.setVehicleRegNo(visitor.getVehicleRegNo());
        dto.setVisitPurpose(visitor.getVisitPurpose());
        dto.setTimeIn(visitor.getTimeIn());
        dto.setTimeOut(visitor.getTimeOut());
        dto.setPhoneNumber(visitor.getPhoneNumber());
        dto.setVisitorType(visitor.getVisitorType());
        dto.setActiveVisitor(visitor.isActiveVisitor());
        dto.setVisitDuration(visitor.getVisitDuration());

        Resident r = visitor.getResident();
        if (r != null) {
            ResidentDTO resDto = new ResidentDTO(
                    r.getId(),
                    r.getFName(),
                    r.getLName(),
                    r.getFlatNo(),
                    r.getMobileNo(),
                    r.getEmail(),
                    r.getResidentType()
            );
            dto.setResident(resDto);
        }

        return dto;
    }

}
