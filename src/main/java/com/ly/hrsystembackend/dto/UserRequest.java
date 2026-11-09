package com.ly.hrsystembackend.dto;

import com.ly.hrsystembackend.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "Employee Id must not be empty.")
        @NotNull(message = "Employee Id must not be null.")
        String employeeId,

        @NotBlank(message = "Password must not be empty.")
        @NotNull(message = "Password must not be null.")
        @Min(value = 8,message = "Password must be at least 8 characters long.")
        String password,

        @NotNull(message = "Role must not be null.")
        Role role,

        Boolean isActive
) {
}
