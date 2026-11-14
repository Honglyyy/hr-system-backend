package com.ly.hrsystembackend.dto.userDto;

import com.ly.hrsystembackend.enums.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        String employeeId,
        Role role,
        Boolean isActive
) {
}
