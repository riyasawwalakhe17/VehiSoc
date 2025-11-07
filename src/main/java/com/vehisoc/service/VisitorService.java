package com.vehisoc.service;

import com.vehisoc.dto.VisitorResponseDTO;
import com.vehisoc.entity.Visitors;

public interface VisitorService {

    String addVisitor(Visitors visitor, Integer residentId);

    VisitorResponseDTO getVisitorByRegNo(String regNo);

}
