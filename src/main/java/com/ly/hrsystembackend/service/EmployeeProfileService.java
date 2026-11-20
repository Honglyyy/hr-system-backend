package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileUpdateRequest;
import com.ly.hrsystembackend.enums.EmployeeStatus;
import com.ly.hrsystembackend.exception.EmployeeNotFoundException;
import com.ly.hrsystembackend.exception.PositionNotFoundException;
import com.ly.hrsystembackend.mapper.EmployeeProfileMapper;
import com.ly.hrsystembackend.model.EmployeeProfile;
import com.ly.hrsystembackend.model.Position;
import com.ly.hrsystembackend.repository.EmployeeProfileRepository;
import com.ly.hrsystembackend.repository.PositionRepository;
import com.ly.hrsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;
    private final CloudinaryService cloudinaryService;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    @Transactional
    public EmployeeProfileResponse addEmployeeProfile(MultipartFile file, EmployeeProfileRequest employeeProfileRequest) throws IOException {
        EmployeeProfile emp = employeeProfileMapper.toEmployeeProfile(employeeProfileRequest);
        Map<?, ?> uploadRes =  cloudinaryService.uploadImageToCloudinary(file, "emp");

        emp.setProfilePictureUrl(uploadRes.get("secure_url").toString());
        emp.setProfilePictureId(uploadRes.get("public_id").toString());

        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(emp));
    }

    @Transactional
    public EmployeeProfileResponse updateEmployeeProfile(UUID id, EmployeeProfileUpdateRequest request) {
        EmployeeProfile existingEmployeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Profile Not Found!"));

        Position position = positionRepository.findById(request.positionId())
                .orElseThrow(() -> new PositionNotFoundException("Position Not Found"));

        EmployeeProfile manager = null;
        if (request.managerId() != null && !request.managerId().isBlank()) {
            manager = employeeProfileRepository.findByEmployeeId(request.managerId())
                    .orElseThrow(() -> new EmployeeNotFoundException("Manager Not Found"));

            if (existingEmployeeProfile.getId().equals(manager.getId())) {
                throw new IllegalArgumentException("Employee cannot be their own manager");
            }
        }

        existingEmployeeProfile.setEmployeeId(request.employeeId());
        existingEmployeeProfile.setFirstName(request.firstName());
        existingEmployeeProfile.setLastName(request.lastName());
        existingEmployeeProfile.setEmail(request.email());
        existingEmployeeProfile.setPhoneNumber(request.phoneNumber());
        existingEmployeeProfile.setPosition(position);
        existingEmployeeProfile.setManager(manager);
        existingEmployeeProfile.setSalary(request.salary());
        existingEmployeeProfile.setStatus(request.employeeStatus());
        existingEmployeeProfile.setHireDate(request.hireDate());

        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(existingEmployeeProfile));
    }

    @Transactional
    public EmployeeProfileResponse updateEmployeeProfilePicture(UUID id, MultipartFile file) throws IOException {
        EmployeeProfile existingEmployeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee Profile Not Found!"));

        if(existingEmployeeProfile.getProfilePictureUrl() != null && !existingEmployeeProfile.getProfilePictureUrl().isBlank()){
            cloudinaryService.deleteImageFromCloudinary(existingEmployeeProfile.getProfilePictureId());
        }


        Map<?, ?> uploadRes =  cloudinaryService.uploadImageToCloudinary(file, "/emp");

        existingEmployeeProfile.setProfilePictureUrl(uploadRes.get("secure_url").toString());
        existingEmployeeProfile.setProfilePictureId(uploadRes.get("public_id").toString());

        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(existingEmployeeProfile));
    }


    @Transactional
    public EmployeeProfileResponse deleteEmployeeProfilePicture(UUID id) throws IOException {
        EmployeeProfile existingEmployeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee Profile Not Found!"));

        if(existingEmployeeProfile.getProfilePictureUrl() != null && !existingEmployeeProfile.getProfilePictureUrl().isBlank()){
            cloudinaryService.deleteImageFromCloudinary(existingEmployeeProfile.getProfilePictureId());
        }

        existingEmployeeProfile.setProfilePictureUrl(null);
        existingEmployeeProfile.setProfilePictureId(null);

        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(existingEmployeeProfile));
    }

    @Transactional
    public EmployeeProfileResponse revokeEmployeeEmployment(UUID id) {
        EmployeeProfile existingEmployeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Profile Not Found!"));

        if (existingEmployeeProfile.getStatus() == EmployeeStatus.TERMINATED) {
            return employeeProfileMapper.toEmployeeProfileResponse(existingEmployeeProfile);
        }

        existingEmployeeProfile.setStatus(EmployeeStatus.TERMINATED);

        userRepository.findByEmployeeId(existingEmployeeProfile.getEmployeeId())
                .ifPresent(user -> {
                    user.setIsActive(false);
                    userRepository.save(user);
                });

        return employeeProfileMapper.toEmployeeProfileResponse(
                employeeProfileRepository.save(existingEmployeeProfile)
        );
    }
}
