package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	@Query(value="Select c.id,c.courseName, c.maxCap, count(*) from Course c join c.lecturer l join c.enrolment e where l.id like :id group by c")
	List<Object[]> findCourseDataByLecturerId(@Param("id")Integer id);
	
	
}
