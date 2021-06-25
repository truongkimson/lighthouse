package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {

    Collection<Enrolment> findEnrolmentsByCourse(Course course);
}


//Collection<Enrol>
//@Query("Select * from student s, enrolment e where s.id = e.student_id and s.id = 5")




