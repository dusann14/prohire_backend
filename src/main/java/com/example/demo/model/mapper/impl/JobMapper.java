package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.Job;
import com.example.demo.model.domainDTO.EmployerDTO;
import com.example.demo.model.domainDTO.JobDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JobMapper implements EntityDTOMapper<Job, JobDTO> {

    private final EmployerMapper employerMapper;

    private final WorkFieldMapper workFieldMapper;
    @Override
    public Job mapDTOToEntity(JobDTO jobDTO) {
        return Job.builder()
                .id(jobDTO.getId())
                .jobTitle(jobDTO.getJobTitle())
                .briefDescription(jobDTO.getBriefDescription())
                .text(jobDTO.getText())
                .postedDate(jobDTO.getPostedDate())
                .expirationDate(jobDTO.getExpirationDate())
                .employer(employerMapper.mapDTOToEntity(jobDTO.getEmployerDTO()))
                .workField(workFieldMapper.mapDTOToEntity(jobDTO.getWorkFieldDTO()))
                .build();
    }

    @Override
    public JobDTO mapEntityToDTO(Job job) {
        return JobDTO.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .briefDescription(job.getBriefDescription())
                .text(job.getText())
                .postedDate(job.getPostedDate())
                .expirationDate(job.getExpirationDate())
                .employerDTO(employerMapper.mapEntityToDTO(job.getEmployer()))
                .workFieldDTO(workFieldMapper.mapEntityToDTO(job.getWorkField()))
                .build();
    }

    @Override
    public List<Job> mapListDTOToListEntity(List<JobDTO> jobDTOS) {

        List<Job> jobList = new ArrayList<>();

        for (JobDTO jobDTO:
             jobDTOS) {
            jobList.add(mapDTOToEntity(jobDTO));
        }

        return jobList;
    }

    @Override
    public List<JobDTO> mapListEntityToListDTO(List<Job> jobs) {

        List<JobDTO> jobDTOS = new ArrayList<>();

        for (Job job:
             jobs) {
            jobDTOS.add(mapEntityToDTO(job));
        }

        return jobDTOS;
    }
}
