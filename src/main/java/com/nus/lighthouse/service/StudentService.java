package com.nus.lighthouse.service;


import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class StudentService {
    public final StudentRepository studentRepository;
    public final EnrolmentRepository enrolmentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository) {
        this.studentRepository = studentRepository;
        this.enrolmentRepository = enrolmentRepository;
    }

    public Collection<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Collection<Student> getAllStudentsByQuery(String query) {
        return studentRepository.findStudentsByQuery(query);
    }

//    public Collection<Enrolment> getEnrolled(){
//        return (Collection<Enrolment>) enrolmentRepository.getById(22);
//    }

}
