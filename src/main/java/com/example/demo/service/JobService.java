package com.example.demo.service;

import com.example.demo.exception.custom.PastDateException;
import com.example.demo.model.domain.Employer;
import com.example.demo.model.domain.Job;
import com.example.demo.model.domainDTO.*;
import com.example.demo.model.mapper.impl.EmployerMapper;
import com.example.demo.model.mapper.impl.JobMapper;
import com.example.demo.model.request_response.Response;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.JobRepository;
import jakarta.el.ELManager;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private final EmployerRepository employerRepository;

    private final JobMapper jobMapper;

    private final EmployerMapper employerMapper;

    public int countJobs() {
        return (int) jobRepository.count();
    }

    public JobDTO postJob(JobDTO jobDTO) {

        Optional<Employer> employerOptional = employerRepository.findEmployerByAppUserId(jobDTO.getEmployerDTO().getId());

        if(!employerOptional.isPresent())
            throw new DataIntegrityViolationException("Kompanija ne postoji");

        EmployerDTO employerDTO = employerMapper.mapEntityToDTO(employerOptional.get());

        jobDTO.setEmployerDTO(employerDTO);

        Job job = jobMapper.mapDTOToEntity(jobDTO);

        if(job.getExpirationDate().isBefore(LocalDateTime.now()))
            throw new PastDateException("Datum isteka mora biti u buducnosti");

        job.setEmployer(employerOptional.get());
        job = jobRepository.save(job);

        return jobMapper.mapEntityToDTO(job);
    }


    public List<JobDTO> getAllJobs() {

        List<Job> jobs = jobRepository.findAll(Sort.by(Sort.Direction.DESC, "postedDate"));

        return jobMapper.mapListEntityToListDTO(jobs);
    }

    public JobDTO findJobById(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if(!optionalJob.isPresent())
            throw new DataIntegrityViolationException("Posao ne postoji");

        return jobMapper.mapEntityToDTO(optionalJob.get());
    }

    public List<JobDTO> findEmployersJobs(Long id) {

        Optional<Employer> employerOptional = employerRepository.findEmployerByAppUserId(id);

        if(!employerOptional.isPresent())
            throw new DataIntegrityViolationException("Kompanija ne postoji");

        List<Job> jobList = jobRepository.findJobByEmployerId(id);

        return jobMapper.mapListEntityToListDTO(jobList);
    }

    public List<JobDTO> findJobByDate(String criteria) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();

        switch (criteria) {
            case "2":
                startDate = LocalDateTime.now().minusDays(3);
                endDate = LocalDateTime.now();
                break;
            case "3":
                startDate = LocalDateTime.now().minusDays(4);
                endDate = LocalDateTime.now();
                break;
            case "7":
                startDate = LocalDateTime.now().minusDays(8);
                endDate = LocalDateTime.now();
                break;
        }

        List<Job> jobs =jobRepository.findJobsByPostedDateBetween(startDate, endDate);

        System.out.println(startDate);
        System.out.println(endDate);

        return jobMapper.mapListEntityToListDTO(jobs);
    }

    public  List<JobDTO> searchJobs(String text, Long cityId, Long workFieldId) {

        List<Job> jobs = new ArrayList<>();

        if(text != null)
            jobs = jobRepository.findByCriteria(text, cityId, workFieldId);
        else if(cityId != null && workFieldId != null)
            jobs = jobRepository.findJobsByEmployerCityIdAndWorkFieldId(cityId, workFieldId);
        else if(cityId != null && workFieldId == null)
            jobs = jobRepository.findJobsByEmployerCityId(cityId);
        else if(cityId == null && workFieldId != null)
            jobs = jobRepository.findJobsByWorkFieldId(workFieldId);

        return jobMapper.mapListEntityToListDTO(jobs);
    }
}
