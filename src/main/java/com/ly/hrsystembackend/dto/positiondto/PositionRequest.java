package com.ly.hrsystembackend.dto.positiondto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PositionRequest(
        @NotNull(message = "Department ID is required.")
        UUID departmentId,

        @NotNull(message = "Job title ID is required.")
        UUID jobTitleId
) {
}
