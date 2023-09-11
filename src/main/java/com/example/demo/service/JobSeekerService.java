package com.example.demo.service;

import com.example.demo.model.domain.AppUser;
import com.example.demo.model.domain.JobSeeker;
import com.example.demo.repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }
}
