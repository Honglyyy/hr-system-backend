package com.ly.hrsystembackend.dto.userdto;

import com.ly.hrsystembackend.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Employee ID is required.")
        @Size(max = 20, message = "Employee ID must not exceed 20 characters.")
        String employeeId,

        @NotBlank(message = "Password is required.")
        @Size(min = 8, max = 72, message = "Password must be between 8 and 72 characters.")
        String password,

        @NotNull(message = "Role must not be null.")
        Role role
) {
}
