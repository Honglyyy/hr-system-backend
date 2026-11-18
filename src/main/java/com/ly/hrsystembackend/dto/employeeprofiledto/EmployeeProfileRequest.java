package com.ly.hrsystembackend.dto.employeeprofiledto;

import com.ly.hrsystembackend.enums.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EmployeeProfileRequest(
        @NotNull
        String employeeId,

        @NotNull
        String firstName,

        @NotNull
        String lastName,

        @NotNull
        @Email
        String email,

        @NotNull
        @Pattern(regexp = "^(?:\\+855|855|0)[1-9]\\d{7,8}$")
        String phoneNumber,

        @NotNull
        UUID positionId,

        String managerId,

        @NotNull
        EmployeeStatus employeeStatus,

        @NotNull
        LocalDate hireDate,

        @NotNull
        BigDecimal salary,

        MultipartFile profilePicture
) {
}
