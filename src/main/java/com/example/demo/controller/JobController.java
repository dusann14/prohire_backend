package com.example.demo.controller;

import com.example.demo.model.domain.Job;
import com.example.demo.model.domainDTO.CityDTO;
import com.example.demo.model.domainDTO.JobDTO;
import com.example.demo.model.domainDTO.WorkFieldDTO;
import com.example.demo.model.request_response.AuthenticationRequest;
import com.example.demo.model.request_response.AuthenticationResponse;
import com.example.demo.model.request_response.Response;
import com.example.demo.service.JobService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    @GetMapping("/count")
    public ResponseEntity<Integer> countJobs(){
        return ResponseEntity.ok(jobService.countJobs());
    }

    @PostMapping("/post")
    public ResponseEntity<JobDTO> postJob(@RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>(jobService.postJob(jobDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobDTO>> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/find")
    public ResponseEntity<JobDTO> findJobById(@RequestParam("id") Long id){
        return ResponseEntity.ok(jobService.findJobById(id));
    }

    @GetMapping("/employer")
    public ResponseEntity<List<JobDTO>> findEmployersJobs(@RequestParam("id") Long id){
        return ResponseEntity.ok(jobService.findEmployersJobs(id));
    }

    @GetMapping("/posted_date")
    public ResponseEntity<List<JobDTO>> findJobByDate(@RequestParam("date") String criteria){
        return ResponseEntity.ok(jobService.findJobByDate(criteria));
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobDTO>> searchJobs(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "cityId", required = false) Long cityId,
            @RequestParam(value = "workFieldId", required = false) Long workFieldId
    ) {
        return ResponseEntity.ok(jobService.searchJobs(text, cityId, workFieldId));
    }

}
