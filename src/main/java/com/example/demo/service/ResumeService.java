package com.example.demo.service;

import com.example.demo.model.domain.JobSeeker;
import com.example.demo.model.domain.Resume;
import com.example.demo.model.domainDTO.JobSeekerDTO;
import com.example.demo.model.domainDTO.ResumeDTO;
import com.example.demo.model.mapper.impl.JobSeekerMapper;
import com.example.demo.model.mapper.impl.ResumeMapper;
import com.example.demo.repository.JobSeekerRepository;
import com.example.demo.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    private final JobSeekerRepository jobSeekerRepository;

    public Resume findResumeById(String id) {
        return resumeRepository.findById(id).get();
    }

    public ResumeDTO findResumeByJobSeeker(Long id) {

        Optional<Resume> optionalResume = resumeRepository.findByJobSeekerId(id);

        if(!optionalResume.isPresent())
            return null;

        return resumeMapper.mapEntityToDTO(optionalResume.get());
    }

    public ResumeDTO updateResume(ResumeDTO resumeDTO, JobSeekerDTO jobSeekerDTO, MultipartFile cvFile) throws IOException {

        Optional<JobSeeker> optionalJobSeeker = jobSeekerRepository.findById(jobSeekerDTO.getId());

        if(!optionalJobSeeker.isPresent())
            throw new DataIntegrityViolationException("Korisnik ne postoji");

        Optional<Resume> optionalResume = resumeRepository.findByJobSeekerId(jobSeekerDTO.getId());

        Resume resume = Resume.builder()
                .jobSeeker(optionalJobSeeker.get())
                .build();

        if(optionalResume.isPresent())
            resume = optionalResume.get();

        resume.setCvFile(cvFile.getBytes());
        resume.setUploadedDate(LocalDateTime.now());
        resume.setName(resumeDTO.getName());

        resumeRepository.save(resume);

        return resumeMapper.mapEntityToDTO(resume);
    }

}
