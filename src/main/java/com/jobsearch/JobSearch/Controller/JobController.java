package com.jobsearch.JobSearch.Controller;

import com.jobsearch.JobSearch.Exception.ApiRequestException;
import com.jobsearch.JobSearch.Model.Job;
import com.jobsearch.JobSearch.Model.JobPage;
import com.jobsearch.JobSearch.Model.JobSearchCriteria;
import com.jobsearch.JobSearch.Model.Student;
import com.jobsearch.JobSearch.Service.JobService;
import com.jobsearch.JobSearch.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;
    private final StudentService studentService;

    @Autowired
    public JobController(JobService jobService, StudentService studentService) {
        this.jobService = jobService;
        this.studentService = studentService;
    }

    @GetMapping("/AllJobs")
    public List<Job> getAllJobs(){
        return jobService.getJobs();
    }
    @PostMapping("/postJob")
    public ResponseEntity<Job> postJobs(@RequestBody Job job){
        if(jobService.isJobAlreadyPresent(job)){
            throw new ApiRequestException("Duplicate Job!!");
        }
        if(job.getSkills()==null ||job.getSkills().isEmpty()){
            throw new ApiRequestException("Can't Post Job with No Skills.");
        }else if(job.getLocation()==null){
            throw new ApiRequestException("Can't Post job if Location is not provided");
        } else if(job.getCompany()==null){
            throw new ApiRequestException("Can't Post job if Company Name is not provided");
        }else if(job.getExperience()==null){
            throw new ApiRequestException("Can't Post job if Required Experience is not provided");
        }
        else if(job.getDescription()==null){
            throw new ApiRequestException("Can't Post job Description is not provided");
        }
        Job newJob = jobService.postJob(job);
        return new ResponseEntity<>(newJob, HttpStatus.OK);
    }

    @GetMapping("/searchJobs")
    public ResponseEntity<Page<Job>> searchJobs(JobPage jobPage, JobSearchCriteria jobSearchCriteria){
        /*if(jobSearchCriteria.getSkills()==null || jobSearchCriteria.getSkills().isEmpty()){
            throw new ApiRequestException("Require at least 1 skill to search jobs");
        }*/
        if((jobSearchCriteria.getSkills()==null || jobSearchCriteria.getSkills().isEmpty()) && jobSearchCriteria.getLocation()==null && jobSearchCriteria.getExperience()==null && jobSearchCriteria.getDescription()==null && jobSearchCriteria.getCompany()==null && jobSearchCriteria.getType()==null){
            throw new ApiRequestException("Search Requires at least 1 search parameter!!");
        } else {
            return new ResponseEntity<>(jobService.getJobs(jobPage, jobSearchCriteria), HttpStatus.OK);
        }

    }

    @PostMapping("/createStudent")
    public String createStudent(@RequestBody Student student){
        try {
            if (student.getUsername() == null) {
                throw new ApiRequestException("Username is not Provided!");
            } else if (student.getPassword() == null) {
                throw new ApiRequestException("Password is not Provided!");
            } else if (studentService.loadUserByUsername(student.getUsername()).isEnabled()) {
                throw new ApiRequestException("Student Already Exists!");
            }else {
                System.out.println("User Not Found So creating New one. No Exception");
                studentService.createStudent(student);
                return "Student with Username '"+student.getUsername()+"' is created. Now you can use this user to make other requests.";
            }
        }catch (UsernameNotFoundException e){
            System.out.println("User Not Found So creating New one");
            studentService.createStudent(student);
            return "Student with Username '"+student.getUsername()+"' is created. Now you can use this user to make other requests.";
        }
    }

}
