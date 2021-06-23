package com.nus.lighthouse;

import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LighthouseApplication {

    private final StudentRepository studentRepo;

    @Autowired
    public LighthouseApplication(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Student s = new Student();
            studentRepo.save(s);
        };
    }

}
