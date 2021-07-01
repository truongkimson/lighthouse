package com.nus.lighthouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.repo.LecturerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class LecturerServiceImplUnitTest {
	@Autowired
	LecturerService lectservice;
	
	@MockBean
	LecturerRepository lectrepo;
	
	@Test
	@Order(1)
	public void getAllLecturersTest() {
		Lecturer l1 = new Lecturer();
		l1.setFirstName("Mary");
		Lecturer l2 = new Lecturer();
		l2.setFirstName("Jane");
		List<Lecturer> list = new ArrayList<Lecturer>();
		list.add(l1);
		list.add(l2);
		Mockito.when(lectrepo.findAll()).thenReturn(list);
		assertThat(lectrepo.findAll()).isEqualTo(list);
	}
	
	@Test
	@Order(2)
	public void getLecturersByQueryTest() {
		Lecturer lect1 = new Lecturer();
		lect1.setFirstName("Lecturer");
		List<Lecturer> list = new ArrayList<Lecturer>();
		list.add(lect1);
		Mockito.when(lectrepo.findLecturersByQuery("Lecturer")).thenReturn(list);
		assertTrue(lect1.getFirstName().equals(list.get(0).getFirstName()));
	}
}
