package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE CONCAT('%',:query,'%')"
            + "OR s.lastName LIKE CONCAT('%',:query,'%')"
            + "OR s.email LIKE CONCAT('%',:query,'%')"
            + "OR s.phone LIKE CONCAT('%',:query,'%')"
            + "OR s.address LIKE CONCAT('%',:query,'%')")
    Collection<Student> findStudentsByQuery(@Param("query") String query);


	@Query(value="Select s.firstName, s.lastName,c.courseName, e.grade from Student s join s.enrolments e join e.course c where s.id like :id")
	List<Object[]> findStudentEnrolmentsbyStudentId(@Param("id")Integer id);
}
