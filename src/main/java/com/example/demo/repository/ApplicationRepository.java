package com.example.demo.repository;

import com.example.demo.model.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select a.* from application a join job_seeker js on (a.job_seeker_id = js.id) where js.app_user_id = ?1 order by a.application_date desc",
            nativeQuery = true)
    List<Application> findByJobSeekerId(Long id);

    List<Application> findByJobId(Long id);

}
