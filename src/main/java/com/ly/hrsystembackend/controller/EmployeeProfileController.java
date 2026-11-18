package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.service.EmployeeProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employeeprofiles")
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> addEmployeeProfile(@RequestPart MultipartFile file, @Valid @RequestPart EmployeeProfileRequest employeeProfileRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeProfileService.addEmployeeProfile(file,employeeProfileRequest));
    }
}
