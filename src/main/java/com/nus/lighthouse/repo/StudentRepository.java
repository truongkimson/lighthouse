package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Student;
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

    @Query("Select s from Student s where s.id = id")
	List<Student> findstudentById(@Param("id") String id);

}



