package com.nus.lighthouse.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nus.lighthouse.LighthouseApplication;
import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.EnrolmentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class EnrolmentServiceUnitTest {
	@Autowired 
	//EnrolmentServiceImpl enrolservice;
	
	@MockBean
	EnrolmentRepository enrolrepo;
	
	@Test
	@Order(1)
	public void findByIdTest() {
		Course c1 = new Course();
		c1.setCourseName("Web Development");
		Student s1 = new Student();
		s1.setFirstName("Jane");
		Enrolment e1 = new Enrolment();
		//e1.setId(2);
		e1.setStudent(s1);
		e1.setCourse(c1);
		e1.setEnrolmentStatus("Enrolled");
		Optional<Enrolment> opt = Optional.of(e1);
		Mockito.when(enrolrepo.findById(2)).thenReturn(opt);
		//assertThat(enrolservice.findById(2)).isEqualTo(opt);
	}
	
	@Test
	@Order(2)
	public void saveEnrolmentTest() {
		Course c1 = new Course();
		c1.setCourseName("Web Development");
		Student s1 = new Student();
		s1.setFirstName("Jane");
		Enrolment e1 = new Enrolment();
		//e1.setId(2);
		e1.setStudent(s1);
		e1.setCourse(c1);
		e1.setEnrolmentStatus("Enrolled");
		Enrolment saved = enrolrepo.save(e1);
		assertNotNull(saved);
	}
}
