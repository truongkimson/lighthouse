package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.*;
import com.nus.lighthouse.repo.AdminRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminService {
    public final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Collection<Admin> getAdmin(){
        return adminRepository.findAll();
    }


}
