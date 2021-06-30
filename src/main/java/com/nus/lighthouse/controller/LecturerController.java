package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.service.LecturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    public final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturer/all")
    public Collection<Lecturer> getLecturer(){
        return lecturerService.getAllLecturers();
    }
    
    @GetMapping("/timetable/{id}")
    public String getTimetableDetails(@PathVariable(name = "id", required = true)Integer id, Model model){
        Collection<Course> courses =  lecturerService.getlectTimetableDetails(id);
        model.addAttribute("courses", courses);
        return "Lecturer/lecturerTimetable";
    }
}
