package com.example.demo.controller;


import com.example.demo.model.domain.Application;
import com.example.demo.model.domain.Job;
import com.example.demo.model.domain.JobSeeker;
import com.example.demo.model.domain.Resume;
import com.example.demo.model.domainDTO.ApplicationDTO;
import com.example.demo.model.domainDTO.JobDTO;
import com.example.demo.model.domainDTO.JobSeekerDTO;
import com.example.demo.model.domainDTO.ResumeDTO;
import com.example.demo.model.request_response.Response;
import com.example.demo.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/post/file")
    public ResponseEntity<ApplicationDTO> postApplicationWithFile(@RequestPart("jobSeekerDTO") String jobSeekerJson,
                                          @RequestPart("jobDTO") String jobJson,
                                          @RequestPart("resumeDTO") String resumeJson,
                                          @RequestPart("cvFile") MultipartFile cvFile) throws IOException {
        JobSeekerDTO jobSeekerDTO = new ObjectMapper().readValue(jobSeekerJson, JobSeekerDTO.class);
        JobDTO jobDTO = new ObjectMapper().readValue(jobJson, JobDTO.class);
        ResumeDTO resumeDTO = new ObjectMapper().readValue(resumeJson, ResumeDTO.class);
        return new ResponseEntity<>(applicationService.postApplicationWithFile(jobSeekerDTO, jobDTO, resumeDTO, cvFile), HttpStatus.CREATED);
    }

    @PostMapping("/post/profile")
    public ResponseEntity<ApplicationDTO> postApplicationFromProfile(@RequestPart("jobSeekerDTO") String jobSeekerJson,
                                                          @RequestPart("jobDTO") String jobJson) throws IOException {
        JobSeekerDTO jobSeekerDTO = new ObjectMapper().readValue(jobSeekerJson, JobSeekerDTO.class);
        JobDTO jobDTO = new ObjectMapper().readValue(jobJson, JobDTO.class);
        return new ResponseEntity<>(applicationService.postApplicationFromProfile(jobSeekerDTO, jobDTO), HttpStatus.CREATED);
    }

    @GetMapping("/employer")
    public ResponseEntity<List<ApplicationDTO>> findApplicationsByEmployer(@RequestParam("id") Long id){
        return ResponseEntity.ok(applicationService.findApplicationsByEmployer(id));
    }

    @GetMapping("/job")
    public ResponseEntity<List<ApplicationDTO>> findApplicationsByJob(@RequestParam("id") Long id){
        return ResponseEntity.ok(applicationService.findApplicationsByJob(id));
    }

}
