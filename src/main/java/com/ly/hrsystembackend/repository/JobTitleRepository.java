package com.ly.hrsystembackend.repository;

import com.ly.hrsystembackend.model.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobTitleRepository extends JpaRepository<com.ly.hrsystembackend.model.JobTitle, UUID> {
    Optional<JobTitle> findByTitle(String systemAdministrator);
}
