package com.ly.hrsystembackend.dto.employeeprofiledto;

import com.ly.hrsystembackend.enums.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EmployeeProfileRequest(
        @NotBlank(message = "Employee ID is required.")
        @Size(max = 5, message = "Employee ID must not exceed 5 characters.")
        String employeeId,

        @NotBlank(message = "First name is required.")
        @Size(max = 100, message = "First name must not exceed 100 characters.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 100, message = "Last name must not exceed 100 characters.")
        String lastName,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be valid.")
        @Size(max = 255, message = "Email must not exceed 255 characters.")
        String email,

        @NotBlank(message = "Phone number is required.")
        @Pattern(regexp = "^(?:\\+855|855|0)[1-9]\\d{7,8}$", message = "Phone number must be a valid Cambodian number.")
        String phoneNumber,

        @NotNull(message = "Position ID is required.")
        UUID positionId,

        String managerId,

        @NotNull(message = "Employee status is required.")
        EmployeeStatus employeeStatus,

        @NotNull(message = "Hire date is required.")
        LocalDate hireDate,

        @NotNull(message = "Salary is required.")
        @DecimalMin(value = "0.0", inclusive = true, message = "Salary must not be negative.")
        @Digits(integer = 10, fraction = 2, message = "Salary must have at most 10 integer digits and 2 decimal places.")
        BigDecimal salary
) {
}
