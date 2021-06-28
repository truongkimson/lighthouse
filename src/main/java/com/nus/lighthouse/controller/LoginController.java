package com.nus.lighthouse.controller;


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
@RequestMapping("/user/")
public class LoginController {
		
	@Autowired  
	private LoginService loginservice;
	
	
	@RequestMapping("Login")
	public String getLogin(@RequestParam("id") int id,@RequestParam("password") String password){
		
		String b="";
		b=loginservice.FindUser(id, password);
		
		
		if (b=="Admin"){
			return "admin/";
		}
		else if (b=="Lecturer"){			
			return "lecturer/";			
		}
		else if (b=="Student") {
			return "student/";
		}
		else {
			return "Login";
		}
		
		
