package com.ly.hrsystembackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @NotBlank(message = "Employee Id must not be empty.")
        @NotNull(message = "Employee Id must not be null.")
        String employeeId,

        @NotBlank(message = "Password must not be empty.")
        @NotNull(message = "Password must not be null.")
        @Min(value = 8,message = "Password must be at least 8 characters long.")
        String password
) {
}
