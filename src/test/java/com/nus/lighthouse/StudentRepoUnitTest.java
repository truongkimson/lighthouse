package com.nus.lighthouse;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nus.lighthouse.repo.StudentRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepoUnitTest {
	@Autowired
	private StudentRepository srepo;
	@Test
	@Order(1)
	public void showstudentperformance()
	{
		List<Object[]> allcourses= srepo.findStudentEnrolmentsbyStudentId(10);
		for(Object[] x:allcourses)
		{
			String firstname=String.valueOf(x[0]);
			String lastname=String.valueOf(x[1]);
			String coursename=String.valueOf(x[2]);
			String grade=String.valueOf(x[3]);
			System.out.println(firstname+" "+lastname+" "+coursename+" "+grade);
		}
		assert(!allcourses.isEmpty());
	}
}