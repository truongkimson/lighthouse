package com.nus.lighthouse;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nus.lighthouse.domain.User;
import com.nus.lighthouse.repo.StudentRepository;
import com.nus.lighthouse.repo.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=LighthouseApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoUnitTest {
	@Autowired
	private UserRepository urepo;
	@Test
	@Order(1)
	public void finduserbyemail()
	{
		User myUser= urepo.findUserByEmail("ntt@iss.edu.sg");
		System.out.println(myUser);
		assert(myUser!=null);
	}
}
