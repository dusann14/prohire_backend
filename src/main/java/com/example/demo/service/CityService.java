package com.example.demo.service;

import com.example.demo.model.domain.City;
import com.example.demo.model.domainDTO.CityDTO;
import com.example.demo.model.mapper.impl.CityMapper;
import com.example.demo.model.request_response.Response;
import com.example.demo.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;
    public List<CityDTO> getAllCities() {
        return cityMapper.mapListEntityToListDTO(cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
