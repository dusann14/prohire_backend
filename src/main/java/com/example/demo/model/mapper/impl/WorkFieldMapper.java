package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.WorkField;
import com.example.demo.model.domainDTO.WorkFieldDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkFieldMapper implements EntityDTOMapper<WorkField, WorkFieldDTO> {
    @Override
    public WorkField mapDTOToEntity(WorkFieldDTO workFieldDTO) {
        return WorkField.builder()
                .id(workFieldDTO.getId())
                .name(workFieldDTO.getName())
                .build();
    }

    @Override
    public WorkFieldDTO mapEntityToDTO(WorkField workField) {
        return WorkFieldDTO.builder()
                .id(workField.getId())
                .name(workField.getName())
                .build();
    }

    @Override
    public List<WorkField> mapListDTOToListEntity(List<WorkFieldDTO> workFieldDTOS) {

        List<WorkField> workFieldList = new ArrayList<>();

        for (WorkFieldDTO workFieldDTO:
             workFieldDTOS) {
            workFieldList.add(mapDTOToEntity(workFieldDTO));
        }

        return workFieldList;
    }

    @Override
    public List<WorkFieldDTO> mapListEntityToListDTO(List<WorkField> workFields) {

        List<WorkFieldDTO> workFieldDTOS = new ArrayList<>();

        for (WorkField workField:
             workFields) {
            workFieldDTOS.add(mapEntityToDTO(workField));
        }

        return workFieldDTOS;
    }
}
