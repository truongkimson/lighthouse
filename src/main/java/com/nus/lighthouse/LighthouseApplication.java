package com.nus.lighthouse;

import com.nus.lighthouse.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LighthouseApplication {

    private final StudentRepository studentRepo;
    private final LecturerRepository lecturerRepo;
    private final AdminRepository adminRepo;
    private final CourseRepository courseRepo;
    private final EnrolmentRepository enrolmentRepo;

    public LighthouseApplication(StudentRepository studentRepo, LecturerRepository lecturerRepo,
                                 AdminRepository adminRepo, CourseRepository courseRepo,
                                 EnrolmentRepository enrolmentRepo) {
        this.studentRepo = studentRepo;
        this.lecturerRepo = lecturerRepo;
        this.adminRepo = adminRepo;
        this.courseRepo = courseRepo;
        this.enrolmentRepo = enrolmentRepo;
    }

    @Autowired


    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

//    // This method of importing test data doesn't work when trying to insert records
//    // that don't meet validation constraints at the point of import (course start date, enroll by)
//    // Use import.sql to initialize data instead
//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            // populate Student table
//            ClassPathResource csvFileResource = new ClassPathResource("data/student.csv");
//            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
//                String[] values = null;
//                Student s = null;
//                csvReader.readNext();
//                while ((values = csvReader.readNext()) != null) {
//                    s = new Student(values[0], values[1], values[2], values[3], values[4], values[5],
//                            LocalDate.parse(values[6], formatter),
//                            LocalDate.parse(values[7], formatter));
//                    studentRepo.save(s);
//                }
//            }
//
//            // populate Lecturer table
//            csvFileResource = new ClassPathResource("data/lecturer.csv");
//            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
//                String[] values = null;
//                Lecturer l = null;
//                csvReader.readNext();
//                while ((values = csvReader.readNext()) != null) {
//                    l = new Lecturer(values[0], values[1], values[2], values[3], values[4]);
//                    System.out.println(l);
//                    lecturerRepo.save(l);
//                }
//            }
//
//            // populate Admin table
//
//            csvFileResource = new ClassPathResource("data/admin.csv");
//            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
//                String[] values = null;
//                Admin a = null;
//                csvReader.readNext();
//                while ((values = csvReader.readNext()) != null ) {
//                    a = new Admin(values[0], values[1], values[2], values[3]);
//                    adminRepo.save(a);
//                }
//            }
//
//            // Populate Course table
//            csvFileResource = new ClassPathResource("data/course.csv");
//            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
//                String[] values = null;
//                Course c = null;
//                csvReader.readNext();
//                while((values = csvReader.readNext()) != null) {
//                    c = new Course(values[0], values[1], Integer.parseInt(values[2]),
//                            Integer.parseInt(values[3]),
//                            Integer.parseInt(values[4]),
//                            LocalDate.parse(values[5], formatter),
//                            LocalDate.parse(values[6], formatter),
//                            LocalDate.parse(values[7], formatter));
//                    int lecturerId = Integer.parseInt(values[8]);
//                    Lecturer lec = lecturerRepo.findById(lecturerId).orElse(null);
//                    c.setLecturer(lec);
//                    courseRepo.save(c);
//                }
//            }
//
//            // Populate Enrolment table
//            csvFileResource = new ClassPathResource("data/enrolment.csv");
//            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileResource.getFile()))) {
//                String[] values = null;
//                Enrolment e = null;
//                csvReader.readNext();
//                while ((values = csvReader.readNext()) != null) {
//                    e = new Enrolment();
//                    int courseId = Integer.parseInt(values[0]);
//                    int studentId = Integer.parseInt(values[1]);
//                    Course course = courseRepo.findById(courseId).orElse(null);
//                    Student student = studentRepo.findById(studentId).orElse(null);
//                    e.setCourse(course);
//                    e.setStudent(student);
//                    e.setRegisteredDate(LocalDate.parse(values[2], formatter));
//                    e.setEnrolmentStatus(values[3]);
//                    e.setGrade(values[4]);
//                    enrolmentRepo.save(e);
//                }
//            }
//
//        };
//    }

}
