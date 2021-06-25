package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("student")
public class StudentController {

    public final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String getStudent(Model model)
    {
        Collection<Student> students =  studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student";
    }

//    public String getEnrolled(Model model)
//    {
//        Collection<Enrolment> enrolls =
//    }


}
