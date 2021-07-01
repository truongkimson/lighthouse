package com.nus.lighthouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository srepo;
	@Transactional
	@Override
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}
	public List<Object[]>findStudentEnrolmentsbyStudentId(int id)
	{
		return srepo.findStudentEnrolmentsbyStudentId(id);
	}
	public List<Object[]>findStudentEnrolmentsbyStudentIdd(int id)
	{
		return srepo.findStudentEnrolmentsbyStudentIdd(id);
	}
	
}
