package com.vehisoc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "First name is mandatory")
    @JsonProperty("fname")
    private String fName;

    @NotBlank(message = "Last name is mandatory")
    @JsonProperty("lname")
    private  String lName;

    @NotBlank(message = "Flat number is mandatory")
    private String flatNo;

    @NotNull(message = "Mobile number is mandatory")
    private long mobileNo;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Resident type is mandatory")
    private ResidentType residentType;



    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicleList;

}
