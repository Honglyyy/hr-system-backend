package com.ly.hrsystembackend.dto.jobtitledto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JobTitleResponse(
        UUID id,
        String title
) {
}
