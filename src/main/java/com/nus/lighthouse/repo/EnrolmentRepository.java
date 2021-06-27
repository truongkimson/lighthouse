package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {

    @Query(value="Select c.courseName, s.firstName, s.lastName, e.grade, e.id from Enrolment e join e.course c join e.student s where c.id like :id")
    List<Object[]> findenrolmentsBycourseId(@Param("id")Integer id);

    Collection<Enrolment> findEnrolmentsByCourse(Course course);
}

