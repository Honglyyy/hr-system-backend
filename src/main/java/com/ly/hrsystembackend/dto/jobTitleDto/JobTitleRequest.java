package com.ly.hrsystembackend.dto.jobTitleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record JobTitleRequest(
        @NotNull
        @NotBlank
        String title
) {
}
