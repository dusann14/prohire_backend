package com.example.demo.service;

import com.example.demo.model.domain.Employer;
import com.example.demo.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;
    public int countEmployers() {
        return (int) employerRepository.count();
    }

    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer findEmployerById(Long id) {

        Optional<Employer> optionalEmployer = employerRepository.findById(id);

        if(!optionalEmployer.isPresent()){
            throw new DataIntegrityViolationException("Kompanija ne postoji");
        }

        return optionalEmployer.get();
    }
}
