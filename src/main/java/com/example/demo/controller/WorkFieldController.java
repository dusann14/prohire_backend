package com.example.demo.controller;

import com.example.demo.model.domain.WorkField;
import com.example.demo.service.WorkFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workfield")
public class WorkFieldController {

    private final WorkFieldService workFieldService;

    @GetMapping("/all")
    public List<WorkField> getAllWorkFields(){
        return workFieldService.getAllWorkFields();
    }

}
