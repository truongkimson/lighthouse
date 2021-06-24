package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.service.LecturerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        return lecturerService.getLecturer();
    }
}
