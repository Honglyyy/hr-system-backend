package com.ly.hrsystembackend.dto.positiondto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PositionRequest(
        @NotNull
        @NotBlank
        UUID departmentId,

        @NotNull
        @NotBlank
        UUID jobTitleId
) {
}
