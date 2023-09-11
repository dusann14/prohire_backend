package com.example.demo.model.domainDTO;

import com.example.demo.model.domain.JobSeeker;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    private Long id;
    private JobDTO jobDTO;
    private JobSeekerDTO jobSeekerDTO;
    private ResumeDTO resumeDTO;
    private LocalDateTime applicationDate;

}
