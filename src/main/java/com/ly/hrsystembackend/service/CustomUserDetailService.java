package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.exception.EmployeeNotFoundException;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        User user = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmployeeId())
                .password(user.getPassword())
                .authorities(user.getRole().toString())
                .build();
    }
}
