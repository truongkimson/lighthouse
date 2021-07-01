package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.exception.CourseFullException;

import java.util.Collection;

public interface AdminService {
    // Course related
    Collection<Course> getAllCourses();
    Course getCourseById(int courseId);
    Collection<Course> getCoursesByQuery(String query);
    void createCourse(Course course, int lecturerId);
    void updateCourse(Course course, int courseId, int lecturerId);
    void deleteCourseById(int courseId);

    // Enrolment related
    Collection<Enrolment> getEnrolmentsByCourse(Course c);
    void enrolStudent(int studentId, int courseId) throws CourseFullException;
    void removeEnrolment(int enrolmentId);
}
