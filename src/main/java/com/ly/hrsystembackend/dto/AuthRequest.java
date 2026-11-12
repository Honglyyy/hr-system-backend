package com.ly.hrsystembackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Employee Id must not be empty.")
        String employeeId,

        @NotBlank(message = "Password must not be empty.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        String password
) {
}
