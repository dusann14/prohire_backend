package com.example.demo.controller;

import com.example.demo.model.domain.Industry;
import com.example.demo.model.domainDTO.IndustryDTO;
import com.example.demo.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/industry")
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping("/all")
    public ResponseEntity<List<IndustryDTO>> getAllIndustries(){
        return ResponseEntity.ok(industryService.getAllIndustries());
    }


}
