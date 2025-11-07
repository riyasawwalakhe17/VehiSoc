package com.vehisoc.repository;

import com.vehisoc.entity.Resident;
import com.vehisoc.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query("SELECT v.resident FROM Vehicle v WHERE v.registrationNumber = :regNo")
    Optional<Resident> findResidentByRegistrationNumber(@Param("regNo") String regNo);

}
