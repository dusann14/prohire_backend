package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    private String jobTitle;
    private String briefDescription;
    private LocalDateTime postedDate;
    private LocalDateTime expirationDate;
    @ManyToOne
    @JoinColumn(
            name = "employer_id"
    )
    private Employer employer;
    @ManyToOne
    @JoinColumn(
            name = "work_field_id"
    )
    private WorkField workField;

    @OneToMany(
            mappedBy = "job",
            orphanRemoval = true
    )
    private Set<Application> applications = new HashSet<>();

}
