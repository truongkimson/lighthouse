package com.nus.lighthouse.controller;

import com.nus.lighthouse.domain.Admin;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //@GetMapping("/admin/all")
    //public Collection<Admin> getAdmin(){
    //    return adminService.getAdmin();
    //}

}
