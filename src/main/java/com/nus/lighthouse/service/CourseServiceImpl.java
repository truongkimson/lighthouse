package com.nus.lighthouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.repo.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseRepository crepo;
	@Transactional
	@Override
	public List<Object[]> findCourseDataByLecturerId(int id) {
		// TODO Auto-generated method stub
		return crepo.findCourseDataByLecturerId(id);
	}
}
