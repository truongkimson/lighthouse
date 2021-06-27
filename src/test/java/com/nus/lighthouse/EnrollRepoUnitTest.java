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

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class EnrollRepoUnitTest {
	@Autowired
	private EnrolmentRepository erepo;
	@Autowired
	private StudentRepository srepo;
	@Test
	@Order(1)
	public void findenrollmentdatabycourseId()
	{
		List<Object[]> allenrolls= erepo.findenrolmentsBycourseId(1);
		for(Object[] row:allenrolls)
		{
		String coursename=String.valueOf(row[0]);
		String firstnamestudent=String.valueOf(row[1]);
		String lastnamestudent=String.valueOf(row[2]);
		String grade=String.valueOf(row[3]);
		int enrolid=(int)(row[4]);
		System.out.println(coursename+" "+firstnamestudent+" "+lastnamestudent+" "+grade+" "+enrolid);
		
		}
		assert(!allenrolls.isEmpty());
	}
	@Test
	@Order(2)
	public void updateenrollmentdata()
	{
		Optional<Enrolment> test=erepo.findById(10);
		test.get().setGrade("A");
		erepo.save(test.get());
		assert(test.isPresent());
	}
}