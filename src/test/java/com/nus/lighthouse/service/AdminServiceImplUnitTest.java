package com.nus.lighthouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.LecturerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AdminServiceImplUnitTest {
	@Autowired
	AdminService adminService;
	
	@MockBean
	CourseRepository courserepo;
	LecturerRepository lectrepo;
	EnrolmentRepository enrolrepo;
	
	@Test
	@Order(1)
	public void getAllCoursesTest() {
		Course c1 = new Course();
		c1.setCourseName("Software Design");
		Course c2 = new Course();
		c2.setCourseName("Web Development");
		List<Course> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		Mockito.when(courserepo.findAll()).thenReturn(list);
		assertThat(adminService.getAllCourses()).isEqualTo(list);
	}
	
	@Test
	@Order(2)
	public void getCourseByIdTest() {
		Course c1 = new Course();
		c1.setId(1);
		Course c2 = new Course();
		c1.setId(2);
		List<Course> list = new ArrayList<Course>();
		list.add(c1);
		list.add(c2);
		Mockito.when(courserepo.findById(1).orElseThrow()).thenReturn(c1);
		assertThat(adminService.getCourseById(1)).isEqualTo(c1);		
	}
	
	@Test
	@Order(3)
	public void getCoursesByQueryTest() {
		Course c1 = new Course();
		c1.setCourseName("Web Development");
		List<Course> list = new ArrayList<Course>();
		list.add(c1);
		Mockito.when(courserepo.findCoursesByQuery("Web Development")).thenReturn(list);
		assertTrue(c1.getCourseName().equals(list.get(0).getCourseName()));
	}
	
	@Test
	@Order(4)
	public void createCourseTest() {
		Course c1 = new Course();
		c1.setId(1);
		Lecturer l1 = lectrepo.findById(11).orElseThrow();
		c1.setLecturer(l1);
		Course saved = courserepo.save(c1);
		assertNotNull(saved);
	}
	
	@Test
	@Order(5)
	public void updateCourseTest() {
		Course c1 = new Course();
		c1.setId(1);
		Lecturer l1 = lectrepo.findById(11).orElseThrow();
		c1.setId(10);
		c1.setLecturer(l1);
		Course saved = courserepo.save(c1);
		assertNotNull(saved);
	}

	@Test
	@Order(6)
	public void deleteCourseByIdTest() {
		Course c1 = new Course("SWD", "Software Design", 10, 40, 12, null, null, null);
		c1.setId(1);
		adminService.deleteCourseById(c1.getId());
		verify(courserepo, times(1)).deleteById(c1.getId());
	}
	
	@Test
	@Order(7)
	public void getEnrolmentsByCourseTest() {
		Enrolment e1 = new Enrolment();
		Student s1 = new Student();
		s1.setFirstName("Peter");
		Course c1 = new Course();
		c1.setCourseName("Course Name");
		e1.setStudent(s1);
		e1.setCourse(c1);
		List<Enrolment> list = new ArrayList<Enrolment>();
		list.add(e1);
		Mockito.when(enrolrepo.findEnrolmentsByCourse(c1)).thenReturn(list);
		assertThat(adminService.getEnrolmentsByCourse(c1)).isEqualTo(e1);
	}
}
