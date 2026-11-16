package com.ly.hrsystembackend.dto.positiondto;

import com.ly.hrsystembackend.dto.departmentdto.DepartmentResponse;
import com.ly.hrsystembackend.dto.jobtitledto.JobTitleResponse;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PositionResponse(
        UUID id,
        String name,
        DepartmentResponse department,
        JobTitleResponse jobTitle
) {
}
