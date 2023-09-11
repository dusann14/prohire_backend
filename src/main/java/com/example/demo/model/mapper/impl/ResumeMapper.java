package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.Resume;
import com.example.demo.model.domainDTO.ResumeDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResumeMapper implements EntityDTOMapper<Resume, ResumeDTO> {
    @Override
    public Resume mapDTOToEntity(ResumeDTO resumeDTO) {
        return Resume.builder()
                .id(resumeDTO.getId())
                .name(resumeDTO.getName())
                .cvFile(resumeDTO.getCvFile())
                .build();
    }

    @Override
    public ResumeDTO mapEntityToDTO(Resume resume) {
        return ResumeDTO.builder()
                .id(resume.getId())
                .name(resume.getName())
                .url(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/resume/")
                        .path(resume.getId())
                        .toUriString())
                .cvFile(resume.getCvFile())
                .build();
    }

    @Override
    public List<Resume> mapListDTOToListEntity(List<ResumeDTO> resumeDTOS) {

        List<Resume> resumeList = new ArrayList<>();

        for (ResumeDTO resumeDTO:
             resumeDTOS) {
            resumeList.add(mapDTOToEntity(resumeDTO));
        }

        return resumeList;
    }

    @Override
    public List<ResumeDTO> mapListEntityToListDTO(List<Resume> resumes) {

        List<ResumeDTO> resumeDTOS = new ArrayList<>();

        for (Resume resume:
             resumes) {
            resumeDTOS.add(mapEntityToDTO(resume));
        }

        return resumeDTOS;
    }
}
