package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.positiondto.PositionRequest;
import com.ly.hrsystembackend.dto.positiondto.PositionResponse;
import com.ly.hrsystembackend.exception.DepartmentNotFoundException;
import com.ly.hrsystembackend.exception.JobTitleNotFoundException;
import com.ly.hrsystembackend.exception.PositionNotFoundException;
import com.ly.hrsystembackend.mapper.PositionMapper;
import com.ly.hrsystembackend.model.Department;
import com.ly.hrsystembackend.model.JobTitle;
import com.ly.hrsystembackend.model.Position;
import com.ly.hrsystembackend.repository.DepartmentRepository;
import com.ly.hrsystembackend.repository.JobTitleRepository;
import com.ly.hrsystembackend.repository.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final JobTitleRepository jobTitleRepository;
    private final PositionMapper positionMapper;

    @Transactional
    public PositionResponse createPosition(PositionRequest positionRequest) {
        departmentRepository.findById(positionRequest.departmentId())
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));

        jobTitleRepository.findById(positionRequest.jobTitleId())
                .orElseThrow(()-> new JobTitleNotFoundException("Job Title not found"));

        Position position = positionRepository.save(positionMapper.toEntity(positionRequest));

        return positionMapper.toPositionResponse(position);
    }

    @Transactional
    public PositionResponse updatePosition(UUID id,PositionRequest positionRequest) {
        Position position = positionRepository.findById(id)
                .orElseThrow(()-> new PositionNotFoundException("Position not found!!"));

        Department department = departmentRepository.findById(positionRequest.departmentId())
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));

        JobTitle jobTitle = jobTitleRepository.findById(positionRequest.jobTitleId())
                .orElseThrow(()-> new JobTitleNotFoundException("Job Title not found"));

        position.setDepartment(department);
        position.setJobTitle(jobTitle);


        return positionMapper.toPositionResponse(positionRepository.save(position));
    }

    @Transactional
    public void deletePosition(UUID id) {
        if (positionRepository.existsById(id)) {
            positionRepository.deleteById(id);
        }
        else {
            throw new PositionNotFoundException("Position not found!!!");
        }
    }

    public List<PositionResponse> getPositions() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toPositionResponse)
                .toList();
    }
}
