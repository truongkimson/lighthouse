package com.nus.lighthouse.service;


import com.nus.lighthouse.domain.*;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StudentService {
    public final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getStudent()
    {
        return studentRepository.findAll();
    }

}
