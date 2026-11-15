package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.departmentDto.DepartmentRequest;
import com.ly.hrsystembackend.dto.departmentDto.DepartmentResponse;
import com.ly.hrsystembackend.exception.DepartmentNotFoundException;
import com.ly.hrsystembackend.mapper.DepartmentMapper;
import com.ly.hrsystembackend.model.Department;
import com.ly.hrsystembackend.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        return departmentMapper.toDepartmentResponse(departmentRepository.save(departmentMapper.toDepartment(departmentRequest)));
    }

     public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
     }

    @Transactional
     public DepartmentResponse updateDepartment(UUID id, DepartmentRequest departmentRequest) throws DepartmentNotFoundException {
         Department department = departmentRepository.findById(id)
                 .orElseThrow(()-> new DepartmentNotFoundException("Department is not found!!!"));

         department.setName(departmentRequest.name());

         return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
     }

    @Transactional
     public void deleteDepartment(UUID id) throws DepartmentNotFoundException {
        departmentRepository.findById(id)
                .orElseThrow(()-> new DepartmentNotFoundException("Department is not found!!!"));
        departmentRepository.deleteById(id);
     }
}
