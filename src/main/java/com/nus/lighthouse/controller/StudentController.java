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

    @GetMapping("/home")
    public String getAllStudent(Model model)
    {
        Collection<Student> students =  studentService.getAllStudents();
        model.addAttribute("students", students);
        return "Student/student";
    }


    @RequestMapping("/enrollCourses")
    public String getSearchedCourses(Model model, @Param("keyword") String keyword){
        Collection<Course> courses = studentService.getSearchedCourses(keyword);
        model.addAttribute("courses",courses);
        model.addAttribute("keyword",keyword);
        System.out.println("This is the course size = "+ courses.size());

        return "Student/studentCourses";
    }




}
