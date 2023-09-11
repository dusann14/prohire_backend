package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.Industry;
import com.example.demo.model.domainDTO.IndustryDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IndustryMapper implements EntityDTOMapper<Industry, IndustryDTO> {
    @Override
    public Industry mapDTOToEntity(IndustryDTO industryDTO) {
        return Industry.builder()
                .id(industryDTO.getId())
                .name(industryDTO.getName())
                .build();
    }

    @Override
    public IndustryDTO mapEntityToDTO(Industry industry) {
        return IndustryDTO.builder()
                .id(industry.getId())
                .name(industry.getName())
                .build();
    }

    @Override
    public List<Industry> mapListDTOToListEntity(List<IndustryDTO> industryDTOS) {

        List<Industry> industryList = new ArrayList<>();

        for (IndustryDTO industryDTO:
             industryDTOS) {
            industryList.add(mapDTOToEntity(industryDTO));
        }
        return industryList;
    }

    @Override
    public List<IndustryDTO> mapListEntityToListDTO(List<Industry> industries) {

        List<IndustryDTO> industryDTOS = new ArrayList<>();

        for (Industry industry:
             industries) {
            industryDTOS.add(mapEntityToDTO(industry));
        }
        return industryDTOS;
    }
}
