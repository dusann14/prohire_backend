package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.JobSeeker;
import com.example.demo.model.domainDTO.JobSeekerDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JobSeekerMapper implements EntityDTOMapper<JobSeeker, JobSeekerDTO> {

    private final AppUserMapper appUserMapper;

    @Override
    public JobSeeker mapDTOToEntity(JobSeekerDTO jobSeekerDTO) {
        return JobSeeker.builder()
                .id(jobSeekerDTO.getId())
                .appUser(appUserMapper.mapDTOToEntity(jobSeekerDTO.getAppUserDTO()))
                .build();
    }

    @Override
    public JobSeekerDTO mapEntityToDTO(JobSeeker jobSeeker) {
        return JobSeekerDTO.builder()
                .id(jobSeeker.getId())
                .appUserDTO(appUserMapper.mapEntityToDTO(jobSeeker.getAppUser()))
                .build();
    }

    @Override
    public List<JobSeeker> mapListDTOToListEntity(List<JobSeekerDTO> jobSeekerDTOS) {

        List<JobSeeker> jobSeekerList = new ArrayList<>();

        for (JobSeekerDTO jobSeekerDTO:
             jobSeekerDTOS) {
            jobSeekerList.add(mapDTOToEntity(jobSeekerDTO));
        }

        return jobSeekerList;
    }

    @Override
    public List<JobSeekerDTO> mapListEntityToListDTO(List<JobSeeker> jobSeekers) {

        List<JobSeekerDTO> jobSeekerDTOS = new ArrayList<>();

        for (JobSeeker jobSeeker:
             jobSeekers) {
            jobSeekerDTOS.add(mapEntityToDTO(jobSeeker));
        }

        return jobSeekerDTOS;
    }
}
