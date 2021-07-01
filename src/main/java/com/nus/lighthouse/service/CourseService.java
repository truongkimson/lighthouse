package com.nus.lighthouse.service;

import java.util.List;

import com.nus.lighthouse.domain.Course;



public interface CourseService {
	public List<Object[]>findCourseDataByLecturerId(int id);
}
