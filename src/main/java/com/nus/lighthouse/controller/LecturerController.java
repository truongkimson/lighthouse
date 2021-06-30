package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.service.LecturerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    public final LecturerServiceImpl lecturerService;

    public LecturerController(LecturerServiceImpl lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturer/all")
    public Collection<Lecturer> getLecturer(){
        return lecturerService.getAllLecturers();
    }
}
