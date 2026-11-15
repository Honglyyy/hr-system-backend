package com.ly.hrsystembackend.utils;

import com.ly.hrsystembackend.enums.EmployeeStatus;
import com.ly.hrsystembackend.enums.Role;
import com.ly.hrsystembackend.exception.EmployeeIdAlreadyExistException;
import com.ly.hrsystembackend.model.*;
import com.ly.hrsystembackend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    @Value("${system.admin.id}")
    private String adminId;
    @Value("${system.admin.password}")
    private String adminPassword;

    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final JobTitleRepository jobTitleRepository;
    private final PositionRepository positionRepository;
    private final EmployeeProfileRepository  employeeProfileRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmployeeId(adminId).isPresent()) {
            DatabaseSeeder.log.info("Admin has already been assigned");
            return;
        }

        Department department = departmentRepository.findByName("Administrator")
                .orElseGet(()-> departmentRepository.save(
                        Department.builder().name("Administrator").build()
                ));

        JobTitle jobTitle = jobTitleRepository.findByTitle("System Administrator")
                .orElseGet(()-> jobTitleRepository.save(
                        JobTitle.builder().title("System Administrator").build()
                ));

        Position adminPosition = positionRepository.findByDepartmentAndJobTitle(department,jobTitle)
                .orElseGet(()-> positionRepository.save(
                        Position.builder().department(department).jobTitle(jobTitle).build()
                ));

        EmployeeProfile adminProfile = employeeProfileRepository.findByEmployeeId(adminId)
                .orElseGet(() -> employeeProfileRepository.save(
                        EmployeeProfile.builder()
                                .employeeId(adminId)
                                .firstName("System")
                                .lastName("Administrator")
                                .email("admin@company.com")
                                .phoneNumber("+85512345678")
                                .position(adminPosition)
                                .manager(null) // Top leadership
                                .status(EmployeeStatus.ACTIVE)
                                .hireDate(LocalDate.now())
                                .salary(new BigDecimal("0.00"))
                                .build()
                ));
        // 6. Create Admin User (Authentication account)
        User adminUser = User.builder()
                .employeeId(adminProfile.getEmployeeId())
                .password(passwordEncoder.encode(adminPassword))
                .role(Role.ADMIN)
                .isActive(true)
                .build();

        userRepository.save(adminUser);
        log.info("Initial Admin successfully seeded with Employee ID: {}", adminId);
    }
}
