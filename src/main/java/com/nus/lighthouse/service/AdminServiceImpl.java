package com.nus.lighthouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.lighthouse.repo.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminRepository arepo;
}
