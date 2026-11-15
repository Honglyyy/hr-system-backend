package com.ly.hrsystembackend.repository;

import com.ly.hrsystembackend.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeProfileRepository extends JpaRepository<com.ly.hrsystembackend.model.EmployeeProfile, UUID> {
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
}
