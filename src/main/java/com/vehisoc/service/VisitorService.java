package com.vehisoc.service;

import com.vehisoc.dto.VisitorResponseDTO;
import com.vehisoc.entity.VisitorType;
import com.vehisoc.entity.Visitors;

import java.util.List;

public interface VisitorService {

    String addVisitor(Visitors visitor, Integer residentId);

    VisitorResponseDTO getVisitorByRegNo(String regNo);

    String updateExitTime(String vehicleRegNo);

    List<VisitorResponseDTO> getActiveVisitors(List<com.vehisoc.entity.VisitorType> types);


}
