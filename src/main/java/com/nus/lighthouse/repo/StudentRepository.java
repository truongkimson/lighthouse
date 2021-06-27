package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Query(value="Select s.firstName, s.lastName,c.courseName, e.grade from Student s join s.enrolments e join e.course c where s.id like :id")
	List<Object[]> findStudentEnrolmentsbyStudentId(@Param("id")Integer id);
}
