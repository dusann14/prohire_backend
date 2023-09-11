package com.example.demo.controller;

import com.example.demo.model.request_response.Response;
import com.example.demo.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employer")
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping("/count")
    public ResponseEntity<Integer> countEmployers(){
        return ResponseEntity.ok(employerService.countEmployers());
    }

}
