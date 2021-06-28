package com.nus.lighthouse.service;


import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.repo.AdminRepository;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LoginService {
    public final AdminRepository adminRepository;
    public final StudetntRepository studentRepository
    public final LecturerRepository lecturerRepository;

    
    
    @Transactional
   	public Student findStudentByName(int id) {
   		return studentRepository.findstudentById(id).get(0);
   	}
    
    @Transactional
   	public Admin findAdminByName(int id) {
   		return adminRepository.findadminById(id).get(0);
   	}
    
    @Transactional
   	public Lecturer findLecturerByName(int id) {
   		return lecturerRepository.findlecturerById(id).get(0);
   	}
    
    public String FindUser(int id,String password){
		String s="";
		if (password==this.findStudentByName(id).getPassword()){
			s="Student";
		}
		else if(password==this.findAdminByName(id).getPassword()) {
			s="Admin";
		}
		else if(password==this.findLecturerByName(id).getPassword()) {
			s="Lecturer";
		}
		
		
		return s;	
	}


}


