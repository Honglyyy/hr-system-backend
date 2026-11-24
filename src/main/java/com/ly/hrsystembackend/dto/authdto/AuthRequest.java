package com.ly.hrsystembackend.dto.authdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Employee ID is required.")
        @Size(max = 20, message = "Employee ID must not exceed 20 characters.")
        String employeeId,

        @NotBlank(message = "Password is required.")
        @Size(min = 8, max = 72, message = "Password must be between 8 and 72 characters.")
        String password
) {
}
