package com.nus.lighthouse.service;


import com.nus.lighthouse.domain.*;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public Collection<Student> getAllStudent()
    {
        return studentRepository.findAll();
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

