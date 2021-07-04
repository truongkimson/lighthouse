package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	@Query(value="Select c.id,c.courseName, c.maxCap, count(c) from Course c join c.lecturer l join c.enrolments e where l.id = :id group by c")
	List<Object[]> findCourseDataByLecturerId(@Param("id")Integer id);

    @Query("SELECT c FROM Course c WHERE c.courseName LIKE CONCAT('%',:query,'%')" +
            "OR c.courseDes LIKE CONCAT('%',:query,'%')")
    Collection<Course> findCoursesByQuery(@Param("query") String query);

    @Query("SELECT DISTINCT c from Course c WHERE c.courseName like %?1%")
    Collection<Course> searchFunction(String keyword);

	@Query(value = " SELECT  * FROM lighthouse.course c "
			+ " WHERE (c.id IN (SELECT en.course_id FROM lighthouse.enrolment en "
			+ " WHERE (en.enrolment_status = 'ENROLLED') "
			+ " AND (en.student_id = (:id)))) ", nativeQuery = true)
	Collection<Course> getTimetableDetails(Integer id);

	@Query(value = " SELECT  * FROM lighthouse.course c "
			+ " WHERE (c.lecturer_id IN (SELECT lec.id FROM lighthouse.lecturer lec "
			+ " WHERE (lec.id = (:id)))) ", nativeQuery = true)
	Collection<Course> getlectTimetableDetails(Integer id);

}
