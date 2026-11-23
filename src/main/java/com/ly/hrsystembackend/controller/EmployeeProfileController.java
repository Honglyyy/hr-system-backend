package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileUpdateRequest;
import com.ly.hrsystembackend.service.EmployeeProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employeeprofiles")
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> addEmployeeProfile(
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("employeeProfileRequest") EmployeeProfileRequest employeeProfileRequest
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeProfileService.addEmployeeProfile(file,employeeProfileRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> updateEmployeeProfile(
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeProfileUpdateRequest employeeProfileUpdateRequest
            ){
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.updateEmployeeProfile(id,employeeProfileUpdateRequest));
    }

    @PatchMapping("/{id}/profile-picture")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> updateEmployeeProfilePicture(
            @PathVariable UUID id,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.updateEmployeeProfilePicture(id,file));
    }

    @DeleteMapping("/{id}/profile-picture")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> deleteEmployeeProfilePicture(
            @PathVariable UUID id
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.deleteEmployeeProfilePicture(id));
    }

    @PostMapping("/{id}/revoke")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<EmployeeProfileResponse> revokeEmployeeProfile(
            @PathVariable UUID id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.revokeEmployeeEmployment(id));
    }
}
