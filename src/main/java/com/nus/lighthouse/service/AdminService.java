package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.repo.AdminRepository;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminService {
    public final AdminRepository adminRepository;
    public final CourseRepository courseRepository;
    public final EnrolmentRepository enrolmentRepository;
    public final LecturerRepository lecturerRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, CourseRepository courseRepository,
                        EnrolmentRepository enrolmentRepository,
                        LecturerRepository lecturerRepository) {
        this.adminRepository = adminRepository;
        this.courseRepository = courseRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    // Course services
    public Collection<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElseThrow();
    }

    public Collection<Course> getCoursesByQuery(String query) {
        return courseRepository.findCoursesByQuery(query);
    }

    public void createCourse(Course course) {
        // set default lecturer to user id 11 (Tin)
        course.setLecturer(lecturerRepository.findById(11).orElseThrow());
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    // Enrolment services
    public Collection<Enrolment> getEnrolmentsByCourse(Course c) {
        return enrolmentRepository.findEnrolmentsByCourse(c);
    }

}
