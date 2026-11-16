package com.ly.hrsystembackend.dto.jobtitledto;

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
