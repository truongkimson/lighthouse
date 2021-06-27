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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/course/create")
    public String createCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "admin/course/create";
    }

    @PostMapping("/course/create")
    public String createCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/course/create";
        }
        System.out.println(course);
        adminService.createCourse(course);
        return "redirect:/admin/course";
    }

    @GetMapping("/course/{courseId}/update")
    public String updateCourse(Model model, @PathVariable("courseId") int courseId) {
        Course course = adminService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "admin/course/update";
    }

    @PostMapping("/course/{courseId}/update")
    public String updateCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult,
                               Model model, @PathVariable("courseId") int courseId) {
        if (bindingResult.hasErrors()) {
            return "/admin/course/update";
        }
        adminService.updateCourse(course);
        return "redirect:/admin/course";
    }

    @GetMapping("/course/{courseId}/delete")
    public String deleteCourse(Model model, @PathVariable("courseId") int courseId) {
        Course course = adminService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "/admin/course/delete";
    }

    @PostMapping("/course/{courseId}/delete")
    public String deleteCourse(@PathVariable("courseId") int courseId) {
        adminService.deleteCourseById(courseId);
        return "redirect:/admin/course";
    }


    // Enrolment related
    @GetMapping("/enrolment/{courseId}")
    public String getEnrolmentsByCourse(Model model, @PathVariable("courseId") int courseId) {
        Course course = adminService.getCourseById(courseId);
        Collection<Enrolment> enrolmentList = adminService.getEnrolmentsByCourse(course);
        model.addAttribute("enrolmentList", enrolmentList);
        model.addAttribute("course", course);
        return "admin/enrolment/detail";
    }
}
