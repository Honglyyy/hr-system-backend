package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleRequest;
import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleResponse;
import com.ly.hrsystembackend.model.JobTitle;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class JobTitleMapper {
    public JobTitleResponse toJobTitleResponse(JobTitle jobTitle) {
        if (jobTitle == null) {
            return null;
        }

        return JobTitleResponse.builder()
                .id(jobTitle.getId())
                .title(jobTitle.getTitle())
                .build();
    }

    public JobTitle toJobTitle(JobTitleRequest jobTitleRequest) {
        if (jobTitleRequest == null) {
            return null;
        }

        return JobTitle.builder()
                .title(jobTitleRequest.title())
                .build();
    }
}
