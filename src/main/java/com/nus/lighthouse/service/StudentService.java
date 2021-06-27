package com.nus.lighthouse.service;

import java.util.List;

import com.nus.lighthouse.domain.Student;

public interface StudentService {
	public List<Student> findAllStudents();
	public List<Object[]>findStudentEnrolmentsbyStudentId(int id);
}
