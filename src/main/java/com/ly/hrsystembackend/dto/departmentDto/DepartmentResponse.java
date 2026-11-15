package com.ly.hrsystembackend.dto.departmentDto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DepartmentResponse(
        UUID id,
        String name
) {
}
