package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.exception.CourseFullException;
import com.nus.lighthouse.exception.CourseNotFoundException;
import com.nus.lighthouse.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AdminServiceImpl implements AdminService {
    public final AdminRepository adminRepository;
    public final CourseRepository courseRepository;
    public final EnrolmentRepository enrolmentRepository;
    public final LecturerRepository lecturerRepository;
    public final StudentRepository studentRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, CourseRepository courseRepository,
                            EnrolmentRepository enrolmentRepository,
                            LecturerRepository lecturerRepository,
                            StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.courseRepository = courseRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
    }

    // Course related services
    public Collection<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int courseId) {
        try {
            return courseRepository.findById(courseId).orElseThrow();
        }
        catch (NoSuchElementException e) {
            throw new CourseNotFoundException("Course not found");
        }
    }

    public Collection<Course> getCoursesByQuery(String query) {
        return courseRepository.findCoursesByQuery(query);
    }

    @Transactional
    public void createCourse(Course course, int lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow();
        course.setLecturer(lecturer);
        courseRepository.save(course);
    }

    @Transactional
    public void updateCourse(Course course, int courseId, int lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow();
        course.setId(courseId);
        course.setLecturer(lecturer);
        courseRepository.save(course);
    }

    @Transactional
    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    // Enrolment services
    public Collection<Enrolment> getEnrolmentsByCourse(Course c) {
        return enrolmentRepository.findEnrolmentsByCourse(c);
    }

    @Transactional
    public void enrolStudent(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (course.getCurrCap() >= course.getMaxCap()) {
            throw new CourseFullException("Course is full");
        }
        Enrolment enrolment = new Enrolment(student, course, LocalDate.now());
        enrolmentRepository.save(enrolment);
    }

    @Transactional
    public void removeEnrolment(int enrolmentId) {
        enrolmentRepository.deleteById(enrolmentId);
    }

}
