package com.example.demo.model.domainDTO;

import com.example.demo.model.domain.City;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployerDTO{

    private Long id;
    private String companyName;
    private CityDTO cityDTO;
    private IndustryDTO industryDTO;

}
