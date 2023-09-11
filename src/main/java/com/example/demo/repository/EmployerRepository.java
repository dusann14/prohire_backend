package com.example.demo.repository;

import com.example.demo.model.domain.Employer;
import com.example.demo.model.domain.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    Optional<Employer> findByCompanyName(String companyName);
    Optional<Employer> findEmployerByAppUserId(Long id);

}
