package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class JobSeeker{

    @Id
    private Long id ;
    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    private AppUser appUser;
    @OneToMany(
            mappedBy = "jobSeeker",
            orphanRemoval = true
    )
    private Set<Application> applications = new HashSet<>();

}
