package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class StudentService {
    public final StudentRepository studentRepository;
    public final EnrolmentRepository enrolmentRepository;
    public final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.courseRepository = courseRepository;
    }

    public Collection<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByQuery(String query) {
        return studentRepository.findStudentsByQuery(query);
    }

    public Collection<Enrolment> getEnrolmentByStudent(Student stu){

        return stu.getEnrolments();
    }

    public Student getDummyStudent(){
        return studentRepository.getById(1);
    }

    public Collection<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Collection<Course> getSearchedCourses(){
        String placeholder = "design";
        return studentRepository.searchFunction(placeholder);
    }
}

