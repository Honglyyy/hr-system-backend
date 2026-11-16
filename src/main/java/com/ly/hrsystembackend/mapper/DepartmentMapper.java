package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.departmentdto.DepartmentRequest;
import com.ly.hrsystembackend.dto.departmentdto.DepartmentResponse;
import com.ly.hrsystembackend.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentResponse toDepartmentResponse(Department department) {
        if (department == null) {
            return null;
        }
        return DepartmentResponse
                .builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public Department toDepartment(DepartmentRequest departmentRequest) {
        if (departmentRequest == null) {
            return null;
        }
        return Department
                .builder()
                .name(departmentRequest.name())
                .build();
    }
}
