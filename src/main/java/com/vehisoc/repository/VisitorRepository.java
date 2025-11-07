package com.vehisoc.repository;

import com.vehisoc.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {
}
