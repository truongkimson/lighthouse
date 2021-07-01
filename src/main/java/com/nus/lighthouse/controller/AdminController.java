package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.exception.CourseFullException;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;
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

    // student related
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

    @GetMapping("/student/create")
    public String createStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "admin/student/create";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute @Valid Student student, BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/student/create";
        }

        try {
            studentService.createStudent(student);
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/student/create";
        }
        return "redirect:/admin/student";
    }

    @GetMapping("/student/{studentId}/update")
    public String updateStudent(Model model, @PathVariable("studentId") int studentId) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "admin/student/update";
    }

    @PostMapping("/student/{studentId}/update")
    public String updateStudent(@ModelAttribute("student") @Valid Student student,
                                BindingResult bindingResult,
                                Model model, @PathVariable("studentId") int studentId) {
        if (bindingResult.hasErrors()) {
            return "admin/student/update";
        }

        try {
            studentService.updateStudent(student, studentId);
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/student/update";
        }

        return "redirect:/admin/student";
    }

    @PostMapping("/student/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/admin/student";
    }

    // lecturer related
    @ModelAttribute("lecturerList")
    public Collection<Lecturer> createLecturerList() {
        return lecturerService.getAllLecturers();
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

    @GetMapping("/lecturer/create")
    public String createLecturer(Model model) {
        Lecturer lecturer = new Lecturer();
        model.addAttribute("lecturer", lecturer);
        return "admin/lecturer/create";
    }

    @PostMapping("/lecturer/create")
    public String createLecturer(@ModelAttribute @Valid Lecturer lecturer,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/lecturer/create";
        }

        try {
            lecturerService.createLecturer(lecturer);
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/lecturer/create";
        }

        return "redirect:/admin/lecturer";
    }

    @GetMapping("/lecturer/{lecturerId}/update")
    public String updateLecturer(Model model, @PathVariable("lecturerId") int lecturerId) {
        Lecturer lecturer = lecturerService.getLecturerById(lecturerId);
        model.addAttribute("lecturer", lecturer);
        return "admin/lecturer/update";
    }

    @PostMapping("/lecturer/{lecturerId}/update")
    public String updateLecturer(@ModelAttribute("lecturer") @Valid Lecturer lecturer,
                                 BindingResult bindingResult,
                                 Model model, @PathVariable("lecturerId") int lecturerId) {
        if (bindingResult.hasErrors()) {
            return "admin/lecturer/update";
        }

        try {
            lecturerService.updateLecturer(lecturer, lecturerId);
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/lecturer/update";
        }

        return "redirect:/admin/lecturer";
    }

    @PostMapping("/lecturer/{lecturerId}/delete")
    public String deleteLecturer(@PathVariable("lecturerId") int lecturerId) {
        lecturerService.deleteLecturerById(lecturerId);
        return "redirect:/admin/lecturer";
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
        Lecturer lecturer = new Lecturer();
        model.addAttribute("course", course);
        model.addAttribute("lecturer", lecturer);
        return "admin/course/create";
    }

    @PostMapping("/course/create")
    public String createCourse(@ModelAttribute("course") @Valid Course course,
                               BindingResult bindingResult,
                               @RequestParam("lecturerId") int lecturerId, Model model) {
        if (bindingResult.hasErrors()) {
            Collection<Lecturer> lecturerList = lecturerService.getAllLecturers();
            model.addAttribute("lecturerList", lecturerList);
            return "admin/course/create";
        }
        adminService.createCourse(course, lecturerId);
        return "redirect:/admin/course";
    }

    @GetMapping("/course/{courseId}/update")
    public String updateCourse(Model model, @PathVariable("courseId") int courseId) {
        Course course = adminService.getCourseById(courseId);
        Lecturer lecturer = adminService.getCourseById(courseId).getLecturer();
        model.addAttribute("course", course);
        model.addAttribute("lecturer", lecturer);
        return "admin/course/update";
    }

    @PostMapping("/course/{courseId}/update")
    public String updateCourse(@ModelAttribute("course") @Valid Course course,
                               BindingResult bindingResult,
                               Model model, @PathVariable("courseId") int courseId,
                               @RequestParam("lecturerId") int lecturerId) {
        if (bindingResult.hasErrors()) {
            Lecturer lecturer = adminService.getCourseById(courseId).getLecturer();
            model.addAttribute("lecturer", lecturer);
            System.out.println(lecturerId);
            System.out.println(course);
            return "/admin/course/update";
        }
        adminService.updateCourse(course, courseId, lecturerId);
        return "redirect:/admin/course";
    }

    @PostMapping("/course/{courseId}/delete")
    public String deleteCourse(@PathVariable("courseId") int courseId) {
        adminService.deleteCourseById(courseId);
        return "redirect:/admin/course";
    }


    // Enrolment related
    @GetMapping("/enrolment")
    public String getCourses(Model model) {
        Collection<Course> courseList = adminService.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "admin/enrolment/index";
    }

    @GetMapping("/enrolment/")
    public String getEnrolmentCoursesByQuery(Model model, @RequestParam("q") String query) {
        Collection<Course> foundCourses = adminService.getCoursesByQuery(query);
        model.addAttribute("courseList", foundCourses);
        return "admin/enrolment/index";
    }


    @GetMapping("/enrolment/{courseId}/detail")
    public String getEnrolmentDetail(Model model, @PathVariable("courseId") int courseId) {
        Course course = adminService.getCourseById(courseId);
        Collection<Enrolment> enrolmentList = adminService.getEnrolmentsByCourse(course);
        model.addAttribute("enrolmentList", enrolmentList);
        model.addAttribute("course", course);
        return "admin/enrolment/detail";
    }

    @GetMapping("/enrolment/{courseId}/create")
    public String createEnrolments(Model model, @PathVariable("courseId") int courseId) {
        Collection<Student> availableStudents =
                studentService.getStudentsAvailToEnrolByCourseId(courseId);
        Course course = adminService.getCourseById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("studentList", availableStudents);
        return "/admin/enrolment/create";
    }

    @GetMapping("/enrolment/{courseId}/create/search/")
    public String createEnrolmentsFilterByQuery(Model model, @PathVariable("courseId") int courseId,
                                                @RequestParam("q") String query) {
        Collection<Student> availableStudents =
                studentService.getStudentsAvailToEnrolByCourseIdAndQuery(courseId, query);
        Course course = adminService.getCourseById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("studentList", availableStudents);
        return "/admin/enrolment/create";
    }

    @PostMapping("/enrolment/{courseId}/create/{studentId}")
    public String createEnrolments(@PathVariable("studentId") int studentId,
                                   @PathVariable("courseId") int courseId, Model model) throws CourseFullException {
        adminService.enrolStudent(studentId, courseId);
        return "redirect:/admin/enrolment/" + courseId + "/create";
    }

    @PostMapping("/enrolment/{courseId}/delete/{enrolmentId}")
    public String deleteEnrolment(@PathVariable("enrolmentId") int enrolmentId,
                                  @PathVariable("courseId") int courseId) {
        adminService.removeEnrolment(enrolmentId);
        return "redirect:/admin/enrolment/" + courseId + "/detail";
    }

}
