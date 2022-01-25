package com.jobsearch.JobSearch.Model;

import com.jobsearch.JobSearch.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner StudentCLR(StudentRepository studentRepository){
        return args -> {
          Student adminUser = new Student(
                  "admin",
                  "admin"
                  );
            studentRepository.saveAll(List.of(adminUser));
        };
    }
}
