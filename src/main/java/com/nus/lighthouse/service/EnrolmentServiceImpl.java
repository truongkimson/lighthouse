package com.nus.lighthouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.repo.EnrolmentRepository;
@Service
public class EnrolmentServiceImpl implements EnrolmentService{
	@Autowired
	private EnrolmentRepository erepo;
	@Transactional
	@Override
	public List<Object[]> findenrolmentsBycourseId(int id) {
		// TODO Auto-generated method stub
		return erepo.findenrolmentsBycourseId(id);
	}
	@Transactional
	@Override
	public Enrolment findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Enrolment> record=erepo.findById(id);
		
		return record.isPresent()?record.get():null;
	}
	@Transactional
	@Override
	public void saveEnrolment(Enrolment enrolment) {
		// TODO Auto-generated method stub
		erepo.save(enrolment);
	}
}
