package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.userDto.UserResponse;
import com.ly.hrsystembackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .employeeId(user.getEmployeeId())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .build();
    }
}
