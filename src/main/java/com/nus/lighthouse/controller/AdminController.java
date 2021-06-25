package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.AdminService;
import com.nus.lighthouse.service.LecturerService;
import com.nus.lighthouse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final AdminService adminService;
    public final StudentService studentService;
    public final LecturerService lecturerService;

    @Autowired
    public AdminController(AdminService adminService, StudentService studentService,
    LecturerService lecturerService) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @RequestMapping(value = {"/student", "/"})
    public String getAllStudents(Model model) {
        Collection<Student> allStudents = studentService.getAllStudents();
        model.addAttribute("studentList", allStudents);
        return "admin/student/index";
    }

    @GetMapping("/student/")
    public String getStudentsByQuery(Model model, @RequestParam("q") String query) {
        Collection<Student> foundStudents = studentService.getAllStudentsByQuery(query);
        model.addAttribute("studentList", foundStudents);
        return "admin/student/index";
    }

    @GetMapping("/lecturer")
    public String getAllLecturers() {
        return "lecturerService.getAllLecturers()";
    }

    @GetMapping("/course")
    public Collection<Course> getAllCourses() { return adminService.getAllCourses(); }

    @GetMapping("/enrolment/{courseId}")
    public Collection<Enrolment> getAllEnrolmentsByCourse(@PathVariable int courseId) {
        return adminService.getAllEnrolmentsByCourse(courseId);
    }


}
