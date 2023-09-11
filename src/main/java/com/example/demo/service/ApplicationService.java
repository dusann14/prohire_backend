package com.example.demo.service;

import com.example.demo.exception.custom.JobExpiredException;
import com.example.demo.model.domain.Application;
import com.example.demo.model.domain.Job;
import com.example.demo.model.domain.JobSeeker;
import com.example.demo.model.domain.Resume;
import com.example.demo.model.domainDTO.*;
import com.example.demo.model.mapper.impl.ApplicationMapper;
import com.example.demo.model.request_response.Response;
import com.example.demo.repository.ApplicationRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.JobSeekerRepository;
import com.example.demo.repository.ResumeRepository;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ResumeRepository resumeRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationDTO postApplicationWithFile(JobSeekerDTO jobSeekerDTO, JobDTO jobDTO, ResumeDTO resumeDTO, MultipartFile cvFile) throws IOException {
        Optional<JobSeeker> optionalJobSeeker = jobSeekerRepository.findById(jobSeekerDTO.getId());

        if(!optionalJobSeeker.isPresent())
            throw new DataIntegrityViolationException("Kompanija ne postoji");

        Optional<Job> optionalJob = jobRepository.findById(jobDTO.getId());

        if(!optionalJob.isPresent())
            throw new DataIntegrityViolationException("Posao ne postoji");

        if(optionalJob.get().getExpirationDate().isAfter(LocalDateTime.now()))
            throw new JobExpiredException("Konkurs za posao je istekao");

        Application application = Application.builder()
                .jobSeeker(optionalJobSeeker.get())
                .job(optionalJob.get())
                .applicationDate(LocalDateTime.now())
                .resume(Resume.builder()
                        .cvFile(cvFile.getBytes())
                        .name(resumeDTO.getName())
                        .uploadedDate(LocalDateTime.now())
                        .build())
                .build();

        application = applicationRepository.save(application);

        return applicationMapper.mapEntityToDTO(application);
    }

    public ApplicationDTO postApplicationFromProfile(JobSeekerDTO jobSeekerDTO, JobDTO jobDTO) {

        Optional<JobSeeker> optionalJobSeeker = jobSeekerRepository.findById(jobSeekerDTO.getId());

        if(!optionalJobSeeker.isPresent())
            throw new DataIntegrityViolationException("Kompanija ne postoji");

        Optional<Job> optionalJob = jobRepository.findById(jobDTO.getId());

        if(!optionalJob.isPresent())
            throw new DataIntegrityViolationException("Posao ne postoji");

        Optional<Resume> optionalResume = resumeRepository.findByJobSeekerId(jobSeekerDTO.getId());

        if(optionalJob.get().getExpirationDate().isBefore(LocalDateTime.now()))
            throw new JobExpiredException("Konkurs za posao je istekao");

        if(!optionalResume.isPresent())
            throw new DataIntegrityViolationException("Biografija za korisnika ne postoji");

        Application application = Application.builder()
                .jobSeeker(optionalJobSeeker.get())
                .job(optionalJob.get())
                .applicationDate(LocalDateTime.now())
                .resume(optionalResume.get())
                .build();

        application = applicationRepository.save(application);

        return applicationMapper.mapEntityToDTO(application);
    }

    public List<ApplicationDTO> findApplicationsByEmployer(Long id) {
        List<Application> applications = applicationRepository.findByJobSeekerId(id);
        return  applicationMapper.mapListEntityToListDTO(applications);
    }

    public List<ApplicationDTO> findApplicationsByJob(Long id) {
        List<Application> applications = applicationRepository.findByJobId(id);
        return  applicationMapper.mapListEntityToListDTO(applications);
    }
}
