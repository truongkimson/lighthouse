package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.StudentService;
import com.nus.lighthouse.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("student")
public class StudentController {

    final
    StudentService studentService;


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
        Student dummy = studentService.getStudentById(1);
        int dummyId = dummy.getId();
        Collection<Enrolment> enrolled = studentService.findEnrolmentByStudentAndStatus(dummyId,"ENROLLED");
        if(enrolled == null){
            return "Student/student";
        }
        else{
            model.addAttribute("enrolls",enrolled);
            return "Student/studentEnrolled";
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

    @GetMapping ("/enrollCourses/description/{courseId}")
    public String getCourseDescription(Model model, @PathVariable("courseId") int courseId){
        Course selected = studentService.getCourseById(courseId);
        model.addAttribute("selected", selected);
        return "Student/studentCoursesDescription";
    }

    @GetMapping("/grades")
    public String getCourseGrades(Model model){
        Student dummy = studentService.getStudentById(10);
        int dummyId = dummy.getId();
        Collection<Enrolment> enrolled = studentService.findEnrolmentByStudentAndStatus(dummyId,"COMPLETED");
        if(enrolled == null){
            return "Student/student";
        }
        else{
            model.addAttribute("enrolls",enrolled);
            model.addAttribute("student",dummy);
            return "Student/studentGrades";
        }
    }


}
