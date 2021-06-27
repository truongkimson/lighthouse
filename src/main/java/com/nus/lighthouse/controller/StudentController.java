package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.*;

@Controller
@RequestMapping("student")
public class StudentController {

    public final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //@GetMapping("/home")
    //public String getStudent(Model model)
    //{
        //Collection<Student> students =  studentService.getStudent();
        //model.addAttribute("students", students);
        //return "student";
    //}

//    public String getEnrolled(Model model)
//    {
//        Collection<Enrolment> enrolls =
//    }


}
