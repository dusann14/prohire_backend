package com.example.demo.repository;

import com.example.demo.model.domain.Employer;
import com.example.demo.model.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findJobByJobTitleAndEmployer(String jobTitle, Employer employer);
    @Query(value = "select j.* from job j where j.employer_id = ?1 order by j.posted_date desc",
            nativeQuery = true)
    List<Job> findJobByEmployerId(Long id);

    List<Job> findJobsByPostedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT j FROM Job j " +
            "WHERE (:text IS NULL OR j.jobTitle LIKE %:text%) " +
            "OR (:text IS NULL OR j.employer.companyName LIKE %:text%) " +
            "AND (:cityId IS NULL OR j.employer.city.id = :cityId) " +
            "AND (:workFieldId IS NULL OR j.workField.id = :workFieldId)")
    List<Job> findByCriteria(@Param("text") String text,
                             @Param("cityId") Long cityId,
                             @Param("workFieldId") Long workFieldId);

    List<Job> findJobsByEmployerCityIdAndWorkFieldId(Long cityId, Long workFieldId);

    List<Job> findJobsByEmployerCityId(Long cityId);

    List<Job> findJobsByWorkFieldId(Long workFieldId);

}
