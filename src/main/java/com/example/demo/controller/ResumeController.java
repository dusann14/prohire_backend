package com.example.demo.controller;

import com.example.demo.model.domain.Resume;
import com.example.demo.model.domainDTO.ApplicationDTO;
import com.example.demo.model.domainDTO.JobDTO;
import com.example.demo.model.domainDTO.JobSeekerDTO;
import com.example.demo.model.domainDTO.ResumeDTO;
import com.example.demo.service.ResumeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Resume resume = resumeService.findResumeById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getName() + "\"")
                .body(resume.getCvFile());
    }

    @PutMapping
    public ResponseEntity<ResumeDTO> updateResume(@RequestPart("resumeDTO") String resumeJson,
                                                  @RequestPart("jobSeekerDTO") String jobSeekerJson,
                                                  @RequestPart("cvFile") MultipartFile cvFile) throws IOException {
        JobSeekerDTO jobSeekerDTO = new ObjectMapper().readValue(jobSeekerJson, JobSeekerDTO.class);
        ResumeDTO resumeDTO = new ObjectMapper().readValue(resumeJson, ResumeDTO.class);
        return ResponseEntity.ok(resumeService.updateResume(resumeDTO, jobSeekerDTO, cvFile));
    }

}
