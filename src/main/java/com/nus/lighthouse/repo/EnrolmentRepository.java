package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {
}
