package com.jobsearch.JobSearch.Model;

import com.jobsearch.JobSearch.Repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JobConfig {
    @Bean
    CommandLineRunner commandLineRunner(JobRepository jobRepository){
        return args -> {
          Job javaDeveloper = new Job(
                  "MicroSoft",
                  "Bangalore",
                  "3-4",
                  "Need Java Developer",
                  "Permanent",
                  9663087069L, Arrays.asList(new String[]{"Java", "MySQL"}));
            Job CPPDeveloper = new Job(
                    "Cisco",
                    "Hyderabad",
                    "7-8",
                    "Need C++ Developer",
                    "Part Time",
                    9663087070L, Arrays.asList(new String[]{"CPP", "MainFrame"}));
            Job SDET3 = new Job(
                    "Interview Kickstart",
                    "Bangalore",
                    "6-9",
                    "Need Automation QA",
                    "Permanent",
                    9663087071L, Arrays.asList(new String[]{"Selenium", "Rest Assured", "Java", "Spring Boot"}));
            Job SDET2 = new Job(
                    "Oracle",
                    "Bangalore",
                    "2-5",
                    "Need Automation QA",
                    "Part Time",
                    9663087072L, Arrays.asList(new String[]{"Java", "Selenium"}));
            Job MSDeveloper = new Job(
                    "Amazon",
                    "Bangalore",
                    "3-5",
                    "Need Java Selenium and MySQL Developer with MicroServices Experience",
                    "Part Time",
                    9663087072L, Arrays.asList(new String[]{"Java", "Selenium", "MySQL"}));
            jobRepository.saveAll(List.of(javaDeveloper, CPPDeveloper, SDET2, SDET3, MSDeveloper));
        };
    }
}
