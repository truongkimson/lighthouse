package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Lecturer;
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

    @GetMapping(value = {"/student", "/"})
    public String getAllStudents(Model model) {
        Collection<Student> allStudents = studentService.getAllStudents();
        model.addAttribute("studentList", allStudents);
        return "admin/student/index";
    }

    @GetMapping("/student/")
    public String getStudentsByQuery(Model model, @RequestParam("q") String query) {
        Collection<Student> foundStudents = studentService.getStudentsByQuery(query);
        model.addAttribute("studentList", foundStudents);
        return "admin/student/index";
    }

    @GetMapping("/lecturer")
    public String getAllLecturers(Model model) {
        Collection<Lecturer> lecturerList = lecturerService.getAllLecturers();
        model.addAttribute("lecturerList", lecturerList);
        return "admin/lecturer/index";
    }

    @GetMapping("/lecturer/")
    public String getLecturersByQuery(Model model, @RequestParam("q") String query) {
        Collection<Lecturer> foundLecturers = lecturerService.getLecturersByQuery(query);
        model.addAttribute("lecturerList", foundLecturers);
        return "admin/lecturer/index";
    }

//  Course related
    @GetMapping("/course")
    public String getAllCourses(Model model) {
        Collection<Course> courseList = adminService.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "admin/course/index";
    }

    @GetMapping("/course/")
    public String getCoursesByQuery(Model model, @RequestParam("q") String query) {
        Collection<Course> foundCourses = adminService.getCoursesByQuery(query);
        model.addAttribute("courseList", foundCourses);
        return "admin/course/index";
    }

    @GetMapping("/enrolment/{courseId}")
    public String getEnrolmentsByCourse(Model model, @PathVariable int courseId) {
        Course course = adminService.getCourseById(courseId);
        Collection<Enrolment> enrolmentList = adminService.getEnrolmentsByCourse(course);
        model.addAttribute("enrolmentList", enrolmentList);
        model.addAttribute("course", course);
        return "admin/enrolment/detail";
    }
}
