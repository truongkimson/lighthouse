package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {

    Collection<Enrolment> findEnrolmentsByCourse(Course course);

    Collection<Enrolment> getEnrolmentByStudent(Student student);

    Collection<Enrolment> getEnrolmentByStudentAndEnrolmentStatusEquals(Student student, String status);

    @Query("SELECT e FROM  Enrolment  e JOIN e.student s WHERE s.Id = :student AND e.enrolmentStatus = :status ")
    Collection<Enrolment> findEnrolmentByStudentAndStatus(@Param("student") int studentId,@Param("status") String status );




}
