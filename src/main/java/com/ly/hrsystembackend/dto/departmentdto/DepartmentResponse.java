package com.ly.hrsystembackend.dto.departmentdto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DepartmentResponse(
        UUID id,
        String name
) {
}
