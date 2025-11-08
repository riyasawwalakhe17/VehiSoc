package com.vehisoc.repository;

import com.vehisoc.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {

    @Query("SELECT v FROM Visitors v JOIN FETCH v.resident r WHERE v.vehicleRegNo = :regNo")
    Optional<Visitors> findVisitorWithResidentByRegNo(@Param("regNo") String regNo);

    Optional<Visitors> findByVehicleRegNo(String vehicleRegNo);
}
