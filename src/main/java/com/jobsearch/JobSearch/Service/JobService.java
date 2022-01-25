package com.jobsearch.JobSearch.Service;
import com.jobsearch.JobSearch.Model.Job;
import com.jobsearch.JobSearch.Model.JobPage;
import com.jobsearch.JobSearch.Model.JobSearchCriteria;
import com.jobsearch.JobSearch.Repository.JobCriteriaRepository;
import com.jobsearch.JobSearch.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobCriteriaRepository jobCriteriaRepository;
    @Autowired
    public JobService(JobRepository jobRepository, JobCriteriaRepository jobCriteriaRepository) {
        this.jobRepository = jobRepository;
        this.jobCriteriaRepository = jobCriteriaRepository;
    }

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    public boolean isJobAlreadyPresent(Job newJob){

        for (Job job: jobRepository.findAll()) {
            if(newJob.equals(job)){
                return true;
            }
        }
        return false;
    }

    public Job postJob(Job job) {
        System.out.println(job.toString());
        Optional<Job> jobOptional = jobRepository.findJobByJobId(job.getJobId());
        if(jobOptional.isPresent()){
            System.out.println("Job Already Present");
            throw new IllegalStateException("Job Already Present");
        }
        return jobRepository.save(job);
    }

    public Page<Job> getJobs(JobPage jobPage, JobSearchCriteria jobSearchCriteria){
        return jobCriteriaRepository.findAllWithFilters(jobPage, jobSearchCriteria);
    }
}
