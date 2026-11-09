package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.UserRequest;
import com.ly.hrsystembackend.enums.Role;
import com.ly.hrsystembackend.exception.EmployeeIdAlreadyExistException;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
