package com.example.demo.model.domainDTO;

import com.example.demo.model.domain.Employer;
import com.example.demo.model.domain.WorkField;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobDTO {

    private Long id;
    private String text;
    private String jobTitle;
    private String briefDescription;
    private LocalDateTime postedDate;
    private LocalDateTime expirationDate;
    private EmployerDTO employerDTO;
    private WorkFieldDTO workFieldDTO;

}
