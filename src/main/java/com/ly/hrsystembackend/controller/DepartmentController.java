package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.departmentDto.DepartmentRequest;
import com.ly.hrsystembackend.dto.departmentDto.DepartmentResponse;
import com.ly.hrsystembackend.exception.DepartmentNotFoundException;
import com.ly.hrsystembackend.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<DepartmentResponse> addDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable UUID id, @Valid @RequestBody DepartmentRequest departmentRequest) throws DepartmentNotFoundException {
        return ResponseEntity.ok(departmentService.updateDepartment(id,departmentRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) throws DepartmentNotFoundException {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }
}
