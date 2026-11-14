package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.AuthRequest;
import com.ly.hrsystembackend.exception.EmployeeNotFoundException;
import com.ly.hrsystembackend.exception.InactiveEmployeeException;
import com.ly.hrsystembackend.exception.LoginFailedException;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.repository.UserRepository;
import com.ly.hrsystembackend.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String authenticate(AuthRequest authRequest) {
        User activeUser = userRepository.findByEmployeeId(authRequest.employeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!!"));

        if (!Boolean.TRUE.equals(activeUser.getIsActive())) {
            log.error("User account is inactive for employee: {}", authRequest.employeeId());
            throw new InactiveEmployeeException("User account is inactive for employee");
        }

        try {
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(activeUser.getEmployeeId(), authRequest.password());
            authenticationManager.authenticate(user);
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new LoginFailedException("Authentication failed");
        }

        return jwtUtil.generateToken(activeUser);
    }
}
