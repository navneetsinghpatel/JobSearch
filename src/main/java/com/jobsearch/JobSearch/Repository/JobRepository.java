package com.jobsearch.JobSearch.Repository;

import com.jobsearch.JobSearch.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE j.jobId = ?1")
    Optional<Job> findJobByJobId(Long jobId);
}
