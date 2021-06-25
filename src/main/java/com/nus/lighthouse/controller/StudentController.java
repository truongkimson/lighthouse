package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getAllStudent(Model model)
    {
        Collection<Student> students =  studentService.getAllStudents();
        model.addAttribute("students", students);
        return "Student/student";
    }

    @GetMapping("/enrolled")
    public String getEnrolmentByStudent(Model model)
    {
        //add dummy student as the session is not done yet
        Student stu =  studentService.getDummyStudent();
        Collection<Enrolment> enrolls = studentService.getEnrolmentByStudent(stu);
        model.addAttribute("enrolls",enrolls);

        return "Student/studentEnrolled";
    }

    @GetMapping("/enrollCourses")
    public String getCoursesToEnroll(Model model){
        Collection<Course> course = studentService.getAllCourse();
        model.addAttribute("courses",course);
        return "Student/studentCourses";
    }

    @GetMapping("/enrollCourses/searched")
    public String getSearchedCourses(Model model){
        Collection<Course>searchedCourse = studentService.getSearchedCourses();
        model.addAttribute("courses",searchedCourse);
        return "Student/studentSearched";
    }


}
