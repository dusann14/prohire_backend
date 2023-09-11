package com.example.demo.controller;

import com.example.demo.model.domain.City;
import com.example.demo.model.domainDTO.CityDTO;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

}
