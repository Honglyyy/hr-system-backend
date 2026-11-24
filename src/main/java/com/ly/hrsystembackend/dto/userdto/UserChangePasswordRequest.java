package com.ly.hrsystembackend.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserChangePasswordRequest (
        @NotBlank(message = "Current password is required.")
        @Size(max = 72, message = "Current password must not exceed 72 characters.")
        String oldPassword,

        @NotBlank(message = "New password is required.")
        @Size(min = 8, max = 72, message = "New password must be between 8 and 72 characters.")
        String newPassword
){
}
