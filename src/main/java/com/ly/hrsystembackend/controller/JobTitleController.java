package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleRequest;
import com.ly.hrsystembackend.dto.jobTitleDto.JobTitleResponse;
import com.ly.hrsystembackend.mapper.JobTitleMapper;
import com.ly.hrsystembackend.model.JobTitle;
import com.ly.hrsystembackend.service.JobTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobtitles")
public class JobTitleController {
    private final JobTitleMapper jobTitleMapper;
    private final JobTitleService jobTitleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<JobTitleResponse> createJobTitle(@Valid @RequestBody JobTitleRequest jobTitleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTitleService.createJobTitle(jobTitleRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<List<JobTitleResponse>> getJobTitles() {
        return ResponseEntity.status(HttpStatus.OK).body(jobTitleService.getJobTitles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTitleResponse> updateJobTitle(@PathVariable UUID id, @Valid @RequestBody JobTitleRequest jobTitleRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(jobTitleService.updateJobTitle(id,jobTitleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTitle(@PathVariable UUID id) {
        jobTitleService.deleteJobTitle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
