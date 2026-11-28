package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileUpdateRequest;
import com.ly.hrsystembackend.enums.EmployeeStatus;
import com.ly.hrsystembackend.service.EmployeeProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<EmployeeProfileResponse> addEmployeeProfile(
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("employeeProfileRequest") EmployeeProfileRequest employeeProfileRequest
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeProfileService.addEmployeeProfile(file,employeeProfileRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileResponse> updateEmployeeProfile(
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeProfileUpdateRequest employeeProfileUpdateRequest
            ){
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.updateEmployeeProfile(id,employeeProfileUpdateRequest));
    }

    @PatchMapping("/{id}/profile-picture")
    public ResponseEntity<EmployeeProfileResponse> updateEmployeeProfilePicture(
            @PathVariable UUID id,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.updateEmployeeProfilePicture(id,file));
    }

    @DeleteMapping("/{id}/profile-picture")
    public ResponseEntity<EmployeeProfileResponse> deleteEmployeeProfilePicture(
            @PathVariable UUID id
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.deleteEmployeeProfilePicture(id));
    }

    @PostMapping("/{id}/revoke")
    public ResponseEntity<EmployeeProfileResponse> revokeEmployeeProfile(
            @PathVariable UUID id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(employeeProfileService.revokeEmployeeEmployment(id));
    }

//    @GetMapping
//    public ResponseEntity<Page<EmployeeProfileResponse>> getEmployeeProfiles(@PageableDefault Pageable pageable){
//        return ResponseEntity.ok().body(employeeProfileService.getEmployeeProfiles(pageable));
//    }

    @GetMapping
    public ResponseEntity<Page<EmployeeProfileResponse>> getEmployeeProfiles(@PageableDefault Pageable page,
                                                                             @RequestParam(required = false) EmployeeStatus status,
                                                                             @RequestParam(required = false) String keyword
    ){
        return ResponseEntity.ok().body(employeeProfileService.getEmployeeProfilesByStatus(page,status,keyword));
    }
}
