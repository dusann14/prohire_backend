package com.example.demo.model.domain;

import com.example.demo.model.request_response.Response;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "job_seeker_id"
    )
    private JobSeeker jobSeeker;
    @ManyToOne
    @JoinColumn(
            name = "job_id"
    )
    private Job job;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    @Cascade(CascadeType.PERSIST)
    private Resume resume;
    private LocalDateTime applicationDate;

}
