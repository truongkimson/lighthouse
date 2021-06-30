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
}
