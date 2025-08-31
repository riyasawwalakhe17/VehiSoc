package com.vehisoc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Data
@Entity
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fName;

    private  String lName;

    private String flatNo;

    private long mobileNo;

    private String email;

    @Enumerated(EnumType.STRING) // <-- Important
    private ResidentType residentType;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;

}
