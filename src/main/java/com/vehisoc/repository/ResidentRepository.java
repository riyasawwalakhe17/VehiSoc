package com.vehisoc.repository;

import com.vehisoc.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    @Query("SELECT r FROM Resident r " +
            "WHERE (:fName IS NULL OR r.fName = :fName) " +
            "AND (:lName IS NULL OR r.lName = :lName)")
    List<Resident> findByFirstAndLastName(@Param("fName") String fName,
                                          @Param("lName") String lName);
}
