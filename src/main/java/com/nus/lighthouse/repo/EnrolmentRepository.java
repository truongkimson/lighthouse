package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {







}




