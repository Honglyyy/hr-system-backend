package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.exception.EmployeeNotFoundException;
import com.ly.hrsystembackend.exception.PositionNotFoundException;
import com.ly.hrsystembackend.model.EmployeeProfile;
import com.ly.hrsystembackend.model.Position;
import com.ly.hrsystembackend.repository.EmployeeProfileRepository;
import com.ly.hrsystembackend.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeProfileMapper {
    private final PositionRepository positionRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeProfileResponse  toEmployeeProfileResponse(EmployeeProfile employeeProfile) {
        return EmployeeProfileResponse.builder()
                .employeeId(employeeProfile.getEmployeeId())
                .firstName(employeeProfile.getFirstName())
                .lastName(employeeProfile.getLastName())
                .email(employeeProfile.getEmail())
                .phoneNumber(employeeProfile.getPhoneNumber())
                .positionId(employeeProfile.getPosition().getId())
                .managerId(employeeProfile.getManager() != null ? employeeProfile.getManager().getEmployeeId() : null)
                .employeeStatus(employeeProfile.getStatus())
                .hireDate(employeeProfile.getHireDate())
                .salary(employeeProfile.getSalary())
                .profilePictureUrl(employeeProfile.getProfilePictureUrl())
                .profilePictureId(employeeProfile.getProfilePictureId())
                .build();
    }

    public EmployeeProfile toEmployeeProfile(EmployeeProfileRequest employeeProfileRequest) {

        Position position = positionRepository.findById(employeeProfileRequest.positionId())
                .orElseThrow(()->new PositionNotFoundException("Position Not Found"));

        EmployeeProfile manager = employeeProfileRepository.findByEmployeeId(employeeProfileRequest.managerId())
                .orElseThrow(() -> new EmployeeNotFoundException("Manager Not Found"));

        return EmployeeProfile.builder()
                .employeeId(employeeProfileRequest.employeeId())
                .firstName(employeeProfileRequest.firstName())
                .lastName(employeeProfileRequest.lastName())
                .email(employeeProfileRequest.email())
                .phoneNumber(employeeProfileRequest.phoneNumber())
                .position(position)
                .manager(manager)
                .status(employeeProfileRequest.employeeStatus())
                .hireDate(employeeProfileRequest.hireDate())
                .salary(employeeProfileRequest.salary())
                .build();
    }

}
