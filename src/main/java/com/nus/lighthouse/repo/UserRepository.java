package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.*;

import domain.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//If we intend to do the login methods, we might want to do it here
public interface UserRepository extends JpaRepository<User,Integer>{
	

}
