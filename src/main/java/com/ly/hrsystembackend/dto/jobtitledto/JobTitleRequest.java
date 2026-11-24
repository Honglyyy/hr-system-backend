package com.ly.hrsystembackend.dto.jobtitledto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record JobTitleRequest(
        @NotBlank(message = "Job title is required.")
        @Size(max = 100, message = "Job title must not exceed 100 characters.")
        String title
) {
}
