package com.ly.hrsystembackend.dto.departmentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DepartmentRequest(
        @NotNull
        @NotBlank
        String name
) {
}
