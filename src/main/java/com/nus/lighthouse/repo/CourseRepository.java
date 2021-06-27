package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {


    @Query("SELECT c FROM Course c WHERE c.courseName LIKE CONCAT('%',:query,'%')" +
            "OR c.courseDes LIKE CONCAT('%',:query,'%')")
    Collection<Course> findCoursesByQuery(@Param("query") String query);

    @Query("SELECT DISTINCT c from Course c WHERE c.courseName like %?1%")
    Collection<Course> searchFunction(String keyword);

	@Query(value="Select c.id,c.courseName, c.maxCap, count(*) from Course c join c.lecturer l join c.enrolment e where l.id like :id group by c")
	List<Object[]> findCourseDataByLecturerId(@Param("id")Integer id);


}
