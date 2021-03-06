package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.security.AppUserDetails;
import com.nus.lighthouse.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("student")
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
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
    public String getEnrolledCourses(Model model){
//        User curr = (User)session.getAttribute("currentUser");
//        int dummyId =curr.getId();
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();


        Collection<Enrolment> enrolled = studentService.findEnrolmentByStudentAndStatus(dummyId,"ENROLLED");
        Collection<Enrolment> completed = studentService.findEnrolmentByStudentAndStatus(dummyId,"COMPLETED");
        if(enrolled == null){
            return "Student/student";
        }
        else{
            model.addAttribute("enrolls",enrolled);
            model.addAttribute("completed",completed);
            return "Student/studentEnrolled";
        }
    }

    @RequestMapping(value = "/enrolled/delete/{id}", method = RequestMethod.POST)
    public String deleteEnrolment(Model model, @PathVariable int id) {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();
        Collection<Enrolment> enrolled = studentService.findEnrolmentByStudentAndStatus(dummyId,"ENROLLED");
        Collection<Enrolment> completed = studentService.findEnrolmentByStudentAndStatus(dummyId,"COMPLETED");
        if(enrolled == null){
            return "Student/student";
        }
        else{
            studentService.deleteEnrolment(id);

            model.addAttribute("enrolls",enrolled);
            model.addAttribute("completed",completed);
            return "redirect:/student/enrolled";
        }
    }


    @RequestMapping("/enrollCourses")
    public String getSearchedCourses(Model model, @Param("keyword") String keyword, @Param("selected")Course selectedCourse){
        Collection<Course> courses = studentService.getSearchedCourses(keyword);
        model.addAttribute("courses",courses);
        model.addAttribute("keyword",keyword);
        model.addAttribute("selected",selectedCourse);

        return "Student/studentCourses";
    }


    @GetMapping("/grades")
    public String getCourseGrades(Model model){
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();
        Student dummy = studentService.getStudentById(dummyId);
//        Collection<Enrolment> enrolled = studentService.findEnrolmentByStudentAndStatus(dummyId,"COMPLETED");
        Collection<Enrolment> enrolled = studentService.findEnrolmentByGradeExists(dummyId);


        if(enrolled == null){
            return "Student/student";
        }
        else{
            model.addAttribute("enrolls",enrolled);
            model.addAttribute("student",dummy);
            return "Student/studentGrades";
        }
    }

    @GetMapping ("/enrollCourses/description/{courseId}")
    public String getCourseDescription(Model model, @PathVariable("courseId") int courseId){
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();
        Student dummy = studentService.getStudentById(dummyId);
        Course selected = studentService.getCourseById(courseId);
        LocalDate now = LocalDate.now();
        String status;
        Boolean ifCanEnrol = false;
        if(selected.getEnrollBy().isBefore(now)){
            status = "You have missed the deadline for registration";
            model.addAttribute("status",status);
        }
        else if(studentService.findEnrolmentByStudentAndStatusAndCourse(dummy.getId(),"ENROLLED",courseId).size()>0){
            status = "You have already enrolled this course";
            model.addAttribute("status",status);
        }
        else if(studentService.ifMaxCapacityExceeded(courseId)){
            status = "The course has reached its maximum capacity for this semester";
            model.addAttribute("status",status);
        }
        else{
            status = "You have successfully enrolled in this course";
            ifCanEnrol = true;
            model.addAttribute("status",status);
        }
        model.addAttribute("ifCanEnrol",ifCanEnrol);
        model.addAttribute("selected", selected);
        return "Student/studentCoursesDescription";
    }

    @RequestMapping("enrollCourses/enrol/{courseId}")
    public String createEnrolment(Model model,@PathVariable("courseId") int courseId){
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();
        Student dummy = studentService.getStudentById(dummyId);
        Course courseEnrol = studentService.getCourseById(courseId);
        LocalDate now = LocalDate.now();
        Enrolment enrolment = new Enrolment(now,"ENROLLED",dummy,courseEnrol);
        String status;
        if(courseEnrol.getEnrollBy().isBefore(now)){
            status = "You have missed the deadline for registration";
            model.addAttribute("status",status);
        }
        else if(studentService.findEnrolmentByStudentAndStatusAndCourse(dummy.getId(),"ENROLLED",courseId).size()>0){
            status = "You have already enrolled this course";
            model.addAttribute("status",status);
        }
        else if(studentService.ifMaxCapacityExceeded(courseId)){
            status = "The course has reached its maximum capacity for this semester";
            model.addAttribute("status",status);
        }
        else{
            status = "You have successfully enrolled in this course";
            studentService.createEnrolment(enrolment);
            model.addAttribute("status",status);
        }
        model.addAttribute("course",courseEnrol);
        return "Student/studentCoursesEnrolled";
    }
    
    @GetMapping("/timetable")
    public String getTimetableDetails(Model model){
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dummyId = userDetails.getUserId();
        Collection<Course> courses =  studentService.getTimetableDetails(dummyId);
        model.addAttribute("courses", courses);
        return "Student/studentTimetable";
    }
//@GetMapping("/timetable/{id}")
//    public String getTimetableDetails(@PathVariable(name = "id", required = true)Integer id, Model model){
//        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        int dummyId = userDetails.getUserId();
//        Collection<Course> courses =  studentService.getTimetableDetails(dummyId);
//        model.addAttribute("courses", courses);
//        return "Student/studentTimetable";
//    }
}
