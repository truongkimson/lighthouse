package com.nus.lighthouse;

import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.StudentRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            ClassPathResource csvFileResource = new ClassPathResource("data/student.csv");
            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
                String[] values = null;
                Student s = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                csvReader.readNext();
                while ((values = csvReader.readNext()) != null) {
                    s = new Student(values[0], values[1], values[2], values[3], values[4], values[5],
                            LocalDate.parse(values[6], formatter),
                            LocalDate.parse(values[7], formatter));
                    System.out.println(s);
                    studentRepo.save(s);
                }
            }
        };
    }

}
