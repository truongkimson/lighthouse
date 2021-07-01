package com.nus.lighthouse;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.repo.CourseRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CoursesRepoUnitTest {
	@Autowired
	private CourseRepository crepo;
	@Test
	@Order(1)
	public void findcoursesbyLecturerId()
	{
		List<Object[]> allcourses= crepo.findCourseDataByLecturerId(12);
		for(Object[] x:allcourses)
		{
			int id=Integer.valueOf(x[0].toString());
			String name=String.valueOf(x[1]);
			int cap=Integer.valueOf(x[2].toString());
			int enrolled=Integer.valueOf(x[3].toString());
			System.out.println(id+" "+name+" "+cap+" "+enrolled);
		}
		assert(!allcourses.isEmpty());
	}
}