package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.userDto.UserRequest;
import com.ly.hrsystembackend.dto.userDto.UserResponse;
import com.ly.hrsystembackend.enums.Role;
import com.ly.hrsystembackend.exception.EmployeeIdAlreadyExistException;
import com.ly.hrsystembackend.mapper.UserMapper;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public String createUser(UserRequest userRequest) {
        if (userRepository.findByEmployeeId(userRequest.employeeId()).isPresent()) {
            throw new EmployeeIdAlreadyExistException("Employee Id already exist.");
        }

        Role role = userRequest.role();
        if (role == Role.ADMIN || role == null) {
            role = Role.EMPLOYEE;
        }

        User user = new User();
        user.setEmployeeId(userRequest.employeeId());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setIsActive(userRequest.isActive());
        userRepository.save(user);

        return "Success";
    }

    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUserResponse);
    }
}
