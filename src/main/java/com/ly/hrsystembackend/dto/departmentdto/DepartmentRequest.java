package com.ly.hrsystembackend.dto.departmentdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record DepartmentRequest(
        @NotBlank(message = "Department name is required.")
        @Size(max = 100, message = "Department name must not exceed 100 characters.")
        String name
) {
}
