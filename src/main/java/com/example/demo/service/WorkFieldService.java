package com.example.demo.service;

import com.example.demo.model.domain.WorkField;
import com.example.demo.repository.WorkFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkFieldService {

    private final WorkFieldRepository workFieldRepository;

    public List<WorkField> getAllWorkFields() {
        return workFieldRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
