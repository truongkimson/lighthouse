package com.nus.lighthouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import com.nus.lighthouse.domain.User;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class StudentServiceImplUnitTest {
	
	@Autowired
	StudentServiceImpl studsvcImpl;
	
	@MockBean
	StudentRepository studrepo;
	CourseRepository courserepo;
	EnrolmentRepository enrolrepo;
	
	@Test
	@Order(1)
	public void getAllStudentsTest() {
		Student s1 = new Student();
		s1.setFirstName("Mary");
		Student s2 = new Student();
		s2.setFirstName("John");
		List<Student> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		Mockito.when(studrepo.findAll()).thenReturn(list);
		assertThat(studsvcImpl.getAllStudents()).isEqualTo(list);
	}
	
	@Test
	@Order(2)
	public void getStudentsByQueryTest() {
		Student stud1 = new Student();
		stud1.setFirstName("Peter");
		List<Student> list = new ArrayList<Student>();
		list.add(stud1);
		Mockito.when(studrepo.findStudentsByQuery("Peter")).thenReturn(list);
		assertTrue(stud1.getFirstName().equals(list.get(0).getFirstName()));
	}
	
	@Test
	@Order(3)
	public void getAllCourseTest() {
		Course c1 = new Course();
		c1.setCourseName("Software Design");
		Course c2 = new Course();
		c2.setCourseName("Web Development");
		List<Course> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		Mockito.when(courserepo.findAll()).thenReturn(list);
		assertThat(studsvcImpl.getAllCourse()).isEqualTo(list);
	}
	
	/*@Test
	@Order(4)
	public void getEnrolmentByStudentAndEnrolmentStatusTest() {
		Enrolment e1 = new Enrolment();
		Student s1 = new Student();
		s1.setFirstName("Tom");
		e1.setEnrolmentStatus("Enrolled");
		e1.setStudent(s1);
		List<Enrolment> list = new ArrayList<Enrolment>();
		list.add(e1);
		Mockito.when(enrolrepo.getEnrolmentByStudentAndEnrolmentStatusEquals(s1, "Enrolled")).thenReturn(list);
		assertThat(studsvcImpl.getEnrolmentByStudentAndEnrolmentStatus(s1, "Enrolled")).isEqualTo(e1);
	}*/
	
	@Test
	@Order(5)
	public void getStudentByIdTest() {
		User s1 = new Student();
		s1.setFirstName("Peter");
		List<Student> list = new ArrayList<Student>();
		list.add((Student) s1);
		Mockito.when(studrepo.getById(1)).thenReturn((Student) s1);
		assertThat(studsvcImpl.getStudentById(1)).isEqualTo(s1);
	}
	
	@Test
	@Order(6) 
	public void findEnrolmentByStudentAndStatusTest() {
		Enrolment e1 = new Enrolment();
		User s1 = new Student();
		s1.setFirstName("Jerry");
		e1.setEnrolmentStatus("Enrolled");
		e1.setStudent((Student) s1);
		List<Enrolment> list = new ArrayList<Enrolment>();
		list.add(e1);
		Mockito.when(enrolrepo.findEnrolmentByStudentAndStatus(1, "Enrolled")).thenReturn(list);
		assertThat(studsvcImpl.findEnrolmentByStudentAndStatus(1, "Enrolled")).isEqualTo(e1);		
	}
	
	@Test
	@Order(7)
	public void getCourseByIdTest() {
		Course c1 = new Course();
		c1.setId(1);
		List<Course> list = new ArrayList<Course>();
		list.add(c1);
		Mockito.when(courserepo.findById(1).orElseThrow()).thenReturn(c1);
		assertThat(studsvcImpl.getCourseById(1)).isEqualTo(c1);	
	}
}
