package com.ly.hrsystembackend.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserChangePasswordRequest (
        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword
){
}
