package com.ly.hrsystembackend.dto.jobTitleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record JobTitleResponse(
        UUID id,
        String title
) {
}
