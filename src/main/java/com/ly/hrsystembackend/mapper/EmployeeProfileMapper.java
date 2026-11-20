package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.model.EmployeeProfile;
import com.ly.hrsystembackend.model.Position;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProfileMapper {

    public EmployeeProfileResponse toEmployeeProfileResponse(EmployeeProfile employeeProfile) {
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

    // Accepts already-resolved entities — no DB calls, no domain exceptions here
    public EmployeeProfile toEmployeeProfile(EmployeeProfileRequest request,
                                             Position position,
                                             EmployeeProfile manager) {
        return EmployeeProfile.builder()
                .employeeId(request.employeeId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .position(position)
                .manager(manager)
                .status(request.employeeStatus())
                .hireDate(request.hireDate())
                .salary(request.salary())
                .build();
    }
}
