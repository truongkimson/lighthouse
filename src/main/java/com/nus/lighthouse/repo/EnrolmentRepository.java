package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {
	@Query(value="Select c.courseName, s.firstName, s.lastName, e.grade, e.id from Enrolment e join e.course c join e.student s where c.id = :id")
	List<Object[]> findenrolmentsBycourseId(@Param("id")Integer id);

    Collection<Enrolment> findEnrolmentsByCourse(Course course);

    Collection<Enrolment> getEnrolmentByStudent(Student student);


    @Query("SELECT e FROM  Enrolment  e JOIN e.student s WHERE s.id = :student AND e.enrolmentStatus = :status ")
    Collection<Enrolment> findEnrolmentByStudentAndStatus(@Param("student") int studentId,@Param("status") String status );

    @Query("SELECT e FROM  Enrolment  e JOIN e.student s JOIN e.course c WHERE s.id = :student AND e.enrolmentStatus = :status AND c.id = :course")
    Collection<Enrolment> findEnrolmentByStudentAndStatusAndCourse(@Param("student") int studentId,@Param("status") String status,@Param("course") int courseId );


}
