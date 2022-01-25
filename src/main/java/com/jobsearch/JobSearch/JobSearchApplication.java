package com.jobsearch.JobSearch;

import com.jobsearch.JobSearch.Repository.JobCriteriaRepository;
import com.jobsearch.JobSearch.Repository.JobRepository;
import com.jobsearch.JobSearch.Repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {StudentRepository.class, JobRepository.class, JobCriteriaRepository.class})
public class JobSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSearchApplication.class, args);
	}

}
