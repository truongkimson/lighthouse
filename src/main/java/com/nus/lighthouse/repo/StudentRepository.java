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
public interface StudentRepository extends JpaRepository<Student, Integer> {


    @Query("SELECT DISTINCT c from Course c WHERE c.courseName like concat('%',:search,'%')")
    Collection<Course> searchFunction(@Param("search") String search);
}



