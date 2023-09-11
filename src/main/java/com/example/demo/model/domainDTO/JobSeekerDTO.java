package com.example.demo.model.domainDTO;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class JobSeekerDTO {

    private Long id;
    private AppUserDTO appUserDTO;
    private Set<ApplicationDTO> applications = new HashSet<>();

}
