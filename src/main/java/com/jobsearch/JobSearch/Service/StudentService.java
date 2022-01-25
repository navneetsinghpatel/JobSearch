package com.jobsearch.JobSearch.Service;

import com.jobsearch.JobSearch.Model.Student;
import com.jobsearch.JobSearch.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements UserDetailsService {

    private StudentRepository studentRepository;

   @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        System.out.println(student.toString());
        Student studentOptional = studentRepository.findByUsername(student.getUsername());
        if(studentOptional!=null){
            System.out.println("Student Already Registered");
            throw new IllegalStateException("Student Already Registered");
        }
        studentRepository.save(student);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if (student == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(student);
    }
}
