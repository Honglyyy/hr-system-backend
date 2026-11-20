package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.positiondto.PositionRequest;
import com.ly.hrsystembackend.dto.positiondto.PositionResponse;
import com.ly.hrsystembackend.exception.DepartmentNotFoundException;
import com.ly.hrsystembackend.exception.JobTitleNotFoundException;
import com.ly.hrsystembackend.model.Department;
import com.ly.hrsystembackend.model.JobTitle;
import com.ly.hrsystembackend.model.Position;
import com.ly.hrsystembackend.repository.DepartmentRepository;
import com.ly.hrsystembackend.repository.JobTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PositionMapper {
    private final JobTitleMapper jobTitleMapper;
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    private final JobTitleRepository jobTitleRepository;

    public PositionResponse toPositionResponse(Position position) {

        if (position == null) {
            return null;
        }

        return PositionResponse.builder()
                .id(position.getId())
                .name(position.getJobTitle().getTitle() + " at " + position.getDepartment().getName())
                .department(departmentMapper.toDepartmentResponse(position.getDepartment()))
                .jobTitle(jobTitleMapper.toJobTitleResponse(position.getJobTitle()))
                .build();
    }

    public Position toEntity(PositionRequest positionRequest,
                             JobTitle jobTitle,
                             Department department) {

        if (positionRequest == null) {
            return null;
        }


        return Position.builder()
                .department(department)
                .jobTitle(jobTitle)
                .build();
    }
}
