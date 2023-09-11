package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.Application;
import com.example.demo.model.domainDTO.ApplicationDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ApplicationMapper implements EntityDTOMapper<Application, ApplicationDTO> {

    private final JobMapper jobMapper;
    private final JobSeekerMapper jobSeekerMapper;
    private final ResumeMapper resumeMapper;

    @Override
    public Application mapDTOToEntity(ApplicationDTO applicationDTO) {
        return Application.builder()
                .id(applicationDTO.getId())
                .job(jobMapper.mapDTOToEntity(applicationDTO.getJobDTO()))
                .jobSeeker(jobSeekerMapper.mapDTOToEntity(applicationDTO.getJobSeekerDTO()))
                .resume(resumeMapper.mapDTOToEntity(applicationDTO.getResumeDTO()))
                .applicationDate(applicationDTO.getApplicationDate())
                .build();
    }

    @Override
    public ApplicationDTO mapEntityToDTO(Application application) {
        return ApplicationDTO.builder()
                .id(application.getId())
                .jobDTO(jobMapper.mapEntityToDTO(application.getJob()))
                .jobSeekerDTO(jobSeekerMapper.mapEntityToDTO(application.getJobSeeker()))
                .resumeDTO(resumeMapper.mapEntityToDTO(application.getResume()))
                .applicationDate(application.getApplicationDate())
                .build();
    }

    @Override
    public List<Application> mapListDTOToListEntity(List<ApplicationDTO> applicationDTOS) {

        List<Application> applicationList = new ArrayList<>();

        for (ApplicationDTO applicationDTO:
             applicationDTOS) {
            applicationList.add(mapDTOToEntity(applicationDTO));
        }

        return applicationList;
    }

    @Override
    public List<ApplicationDTO> mapListEntityToListDTO(List<Application> applications) {

        List<ApplicationDTO> applicationDTOS = new ArrayList<>();

        for (Application application:
             applications) {
            applicationDTOS.add(mapEntityToDTO(application));
        }

        return applicationDTOS;
    }
}
