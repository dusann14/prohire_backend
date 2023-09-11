package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.Employer;
import com.example.demo.model.domainDTO.EmployerDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployerMapper implements EntityDTOMapper<Employer, EmployerDTO> {

    private final IndustryMapper industryMapper;
    private final CityMapper cityMapper;

    @Override
    public Employer mapDTOToEntity(EmployerDTO employerDTO) {
        return Employer.builder()
                .id(employerDTO.getId())
                .companyName(employerDTO.getCompanyName())
                .city(cityMapper.mapDTOToEntity(employerDTO.getCityDTO()))
                .industry(industryMapper.mapDTOToEntity(employerDTO.getIndustryDTO()))
                .build();
    }

    @Override
    public EmployerDTO mapEntityToDTO(Employer employer) {
        return EmployerDTO.builder()
                .id(employer.getId())
                .companyName(employer.getCompanyName())
                .cityDTO(cityMapper.mapEntityToDTO(employer.getCity()))
                .industryDTO(industryMapper.mapEntityToDTO(employer.getIndustry()))
                .build();
    }

    @Override
    public List<Employer> mapListDTOToListEntity(List<EmployerDTO> employerDTOS) {

        List<Employer> employerList = new ArrayList<>();

        for (EmployerDTO employerDTO:
             employerDTOS) {
            employerList.add(mapDTOToEntity(employerDTO));
        }

        return employerList;
    }

    @Override
    public List<EmployerDTO> mapListEntityToListDTO(List<Employer> employers) {

        List<EmployerDTO> employerDTOS = new ArrayList<>();

        for (Employer employer:
             employers) {
            employerDTOS.add(mapEntityToDTO(employer));
        }

        return employerDTOS;
    }
}
