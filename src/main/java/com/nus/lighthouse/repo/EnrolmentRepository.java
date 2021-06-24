package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {
}


//Collection<Enrol>
//@Query("Select * from student s, enrolment e where s.id = e.student_id and s.id = 5")




