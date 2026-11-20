package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.userdto.UserRequest;
import com.ly.hrsystembackend.dto.userdto.UserResponse;
import com.ly.hrsystembackend.enums.EmployeeStatus;
import com.ly.hrsystembackend.enums.Role;
import com.ly.hrsystembackend.exception.EmployeeIdAlreadyExistException;
import com.ly.hrsystembackend.exception.EmployeeNotFoundException;
import com.ly.hrsystembackend.mapper.EmployeeProfileMapper;
import com.ly.hrsystembackend.mapper.UserMapper;
import com.ly.hrsystembackend.model.EmployeeProfile;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.repository.EmployeeProfileRepository;
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
    private final EmployeeProfileRepository employeeProfileRepository;

    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.findByEmployeeId(userRequest.employeeId()).isPresent()) {
            throw new EmployeeIdAlreadyExistException("Employee Id already exist.");
        }
        EmployeeProfile employeeProfile = employeeProfileRepository.findByEmployeeId(userRequest.employeeId())
                .orElseThrow(()->new EmployeeNotFoundException("Employee Id not found."));


        Role role = userRequest.role();
        if (role == Role.ADMIN || role == null) {
            role = Role.EMPLOYEE;
        }

        User user = new User();
        user.setEmployeeId(employeeProfile.getEmployeeId());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setIsActive(
                employeeProfile.getStatus() != EmployeeStatus.TERMINATED ? Boolean.TRUE : Boolean.FALSE
        );
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUserResponse);
    }
}
