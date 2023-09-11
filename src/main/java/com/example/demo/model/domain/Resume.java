package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(
            name = "job_seeker_id",
            nullable = true
    )
    private JobSeeker jobSeeker;
    @Column(columnDefinition = "BLOB")
    private byte[] cvFile;
    private LocalDateTime uploadedDate;
    private String name;
}
