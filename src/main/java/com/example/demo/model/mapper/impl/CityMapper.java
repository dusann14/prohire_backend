package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.City;
import com.example.demo.model.domainDTO.CityDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityMapper implements EntityDTOMapper<City, CityDTO> {
    @Override
    public City mapDTOToEntity(CityDTO cityDTO) {
        return City.builder()
                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .build();
    }

    @Override
    public CityDTO mapEntityToDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    @Override
    public List<City> mapListDTOToListEntity(List<CityDTO> cityDTOS) {

        List<City> cityList = new ArrayList<>();

        for (CityDTO cityDTO:
             cityDTOS) {
            cityList.add(mapDTOToEntity(cityDTO));
        }
        return cityList;
    }

    @Override
    public List<CityDTO> mapListEntityToListDTO(List<City> cities) {

        List<CityDTO> cityDTOS = new ArrayList<>();

        for (City city:
             cities) {
            cityDTOS.add(mapEntityToDTO(city));
        }
        return cityDTOS;
    }


}
