package com.ly.hrsystembackend.repository;

import com.ly.hrsystembackend.model.Department;
import com.ly.hrsystembackend.model.JobTitle;
import com.ly.hrsystembackend.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<com.ly.hrsystembackend.model.Position, UUID> {
    Optional<Position> findByDepartmentAndJobTitle(Department department, JobTitle jobTitle);
}
