package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;
import java.util.Collection;

public interface StudentService {
    Collection<Student> getAllStudents();
    Collection<Student> getStudentsByQuery(String query);
    Student getStudentById(int studentId);
    void createStudent(Student student) throws EmailAlreadyExistsException;
    void updateStudent(Student student, int studentId) throws EmailAlreadyExistsException;
    void deleteStudentById(int studentId);
    Collection<Student> getStudentsAvailToEnrolByCourseId(int courseId);
    Collection<Student> getStudentsAvailToEnrolByCourseIdAndQuery(int courseId, String query);
  
    public Collection<Student> getAllStudents();
    public Collection<Student> getStudentsByQuery(String query);
//    public Collection<Enrolment> getEnrolmentByStudent(Student stu);
    public Collection<Enrolment> getEnrolmentByStudentAndEnrolmentStatus(Student student, String Status);
    public Collection<Course> getAllCourse();
    public Collection<Course> getSearchedCourses(String keyword);
    public Student getStudentById(int id);
    public Collection<Enrolment> findEnrolmentByStudentAndStatus(int student, String status);
    public Course getCourseById(int id);
    public Enrolment createEnrolment(Enrolment enrolment);
    public Collection<Enrolment> findEnrolmentByStudentAndStatusAndCourse(int student, String status, int course);
    public boolean ifMaxCapacityExceeded(int courseId);
    public void deleteEnrolment(int id);
}
