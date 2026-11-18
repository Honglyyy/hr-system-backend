package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileRequest;
import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.mapper.EmployeeProfileMapper;
import com.ly.hrsystembackend.model.EmployeeProfile;
import com.ly.hrsystembackend.repository.EmployeeProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;
    private final CloudinaryService cloudinaryService;

    @Transactional
    public EmployeeProfileResponse addEmployeeProfile(MultipartFile file, EmployeeProfileRequest employeeProfileRequest) throws IOException {
        EmployeeProfile emp = employeeProfileMapper.toEmployeeProfile(employeeProfileRequest);
        Map<?, ?> uploadRes =  cloudinaryService.uploadImageToCloudinary(file, "/emp");

        emp.setProfilePictureUrl(uploadRes.get("secure_url").toString());
        emp.setProfilePictureId(uploadRes.get("public_id").toString());

        return employeeProfileMapper.toEmployeeProfileResponse(employeeProfileRepository.save(emp));
    }
}
