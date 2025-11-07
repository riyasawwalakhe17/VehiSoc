package com.vehisoc.mapper;

import com.vehisoc.dto.ResidentDTO;
import com.vehisoc.entity.Resident;

public class ResidentMapper {

    public static ResidentDTO toDTO(Resident resident) {
        ResidentDTO dto = new ResidentDTO();
        dto.setId(resident.getId());
        dto.setFname(resident.getFName());
        dto.setLname(resident.getLName());
        dto.setFlatNo(resident.getFlatNo());
        dto.setMobileNo(resident.getMobileNo());
        dto.setEmail(resident.getEmail());
        dto.setResidentType(resident.getResidentType());
        return dto;
    }
}
