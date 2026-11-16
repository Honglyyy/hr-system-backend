package com.ly.hrsystembackend.service;

import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleRequest;
import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleResponse;
import com.ly.hrsystembackend.exception.JobTitleNotFoundException;
import com.ly.hrsystembackend.mapper.JobTitleMapper;
import com.ly.hrsystembackend.model.JobTitle;
import com.ly.hrsystembackend.repository.JobTitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobTitleService {
    private final JobTitleRepository jobTitleRepository;
    private final JobTitleMapper jobTitleMapper;

    @Transactional
    public JobTitleResponse createJobTitle(JobTitleRequest jobTitleRequest) {
        return jobTitleMapper.toJobTitleResponse(
                jobTitleRepository.save(
                        jobTitleMapper.toJobTitle(jobTitleRequest)
                    )
        );
    }

    public List<JobTitleResponse> getJobTitles() {
        return jobTitleRepository.findAll()
                .stream()
                .map(jobTitleMapper::toJobTitleResponse)
                .toList();
    }

    @Transactional
    public JobTitleResponse updateJobTitle(UUID id, JobTitleRequest jobTitleRequest) {
        JobTitle jobTitle = jobTitleRepository.findById(id)
                .orElseThrow(()-> new JobTitleNotFoundException("Job Title Not Found!"));

        jobTitle.setTitle(jobTitleRequest.title());

        return jobTitleMapper.toJobTitleResponse(jobTitleRepository.save(jobTitle));
    }

    @Transactional
    public void deleteJobTitle(UUID id) {
        jobTitleRepository.findById(id)
                .orElseThrow(()-> new JobTitleNotFoundException("Job Title Not Found!"));

        jobTitleRepository.deleteById(id);
    }
}
