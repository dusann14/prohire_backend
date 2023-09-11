package com.example.demo.service;

import com.example.demo.model.domain.Industry;
import com.example.demo.model.domainDTO.IndustryDTO;
import com.example.demo.model.mapper.impl.IndustryMapper;
import com.example.demo.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository industryRepository;

    private final IndustryMapper industryMapper;
    public List<IndustryDTO> getAllIndustries() {
        return industryMapper.mapListEntityToListDTO(industryRepository.findAll());
    }
}
