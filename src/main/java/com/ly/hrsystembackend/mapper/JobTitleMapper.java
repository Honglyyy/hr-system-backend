package com.ly.hrsystembackend.mapper;

import com.ly.hrsystembackend.dto.jobtitledto.JobTitleRequest;
import com.ly.hrsystembackend.dto.jobtitledto.JobTitleResponse;
import com.ly.hrsystembackend.model.JobTitle;
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
