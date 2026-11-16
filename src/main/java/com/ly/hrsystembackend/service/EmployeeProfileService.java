package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.mapper.EmployeeProfileMapper;
import com.ly.hrsystembackend.repository.EmployeeProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;

    @Transactional
    public EmployeeProfileResponse addEmployeeProfile(EmployeeProfileRequest employeeProfileRequest) {
        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(employeeProfileMapper.toEmployeeProfile(employeeProfileRequest)));
    }
}
