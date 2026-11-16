package com.ly.hrsystembackend.dto.employeeprofiledto;

import com.ly.hrsystembackend.enums.EmployeeStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Builder
public record EmployeeProfileResponse(
        String employeeId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        UUID positionId,
        String managerId,
        EmployeeStatus employeeStatus,
        LocalDate hireDate,
        BigDecimal salary
) {
}
