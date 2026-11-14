package com.ly.hrsystembackend.dto;

import com.ly.hrsystembackend.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Employee Id must not be empty.")
        @NotNull(message = "Employee Id must not be null.")
        String employeeId,

        @NotBlank(message = "Password must not be empty.")
        @NotNull(message = "Password must not be null.")
        @Size( min = 8,message = "Password must be at least 8 characters long.")
        String password,

        @NotNull(message = "Role must not be null.")
        Role role,

        Boolean isActive
) {
}
