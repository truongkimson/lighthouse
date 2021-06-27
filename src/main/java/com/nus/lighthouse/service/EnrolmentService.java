package com.nus.lighthouse.service;

import java.util.List;


import com.nus.lighthouse.domain.Enrolment;

public interface EnrolmentService {
	public List<Object[]> findenrolmentsBycourseId(int id);

	public Enrolment findById(Integer id);

	public void saveEnrolment(Enrolment enrolment);
}
