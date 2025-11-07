package com.vehisoc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vehisoc.entity.ResidentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDTO {

    private int id;

    @JsonProperty("fname")
    private String fname;

    @JsonProperty("lname")
    private String lname;

    private String flatNo;
    private long mobileNo;
    private String email;
    private ResidentType residentType;
}
