package com.ly.hrsystembackend.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.model.EmployeeProfile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeProfileRepository extends
        JpaRepository<com.ly.hrsystembackend.model.EmployeeProfile, UUID>,
        JpaSpecificationExecutor<EmployeeProfile>{
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    @EntityGraph(attributePaths = {"position", "manager"})
    Page<EmployeeProfile> findAll(Specification<EmployeeProfile> specification, Pageable pageable);

}
