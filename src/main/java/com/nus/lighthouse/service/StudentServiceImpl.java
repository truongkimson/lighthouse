package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService{

    final
    StudentRepository studentRepository;
    final
    EnrolmentRepository enrolmentRepository;
    final
    CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.courseRepository = courseRepository;
    }


//    public StudentServiceImpl(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository, CourseRepository courseRepository) {
//        this.studentRepository = studentRepository;
//        this.enrolmentRepository = enrolmentRepository;
//        this.courseRepository = courseRepository;
//    }

    @Transactional
    public Collection<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    @Transactional
    public Collection<Student> getStudentsByQuery(String query) {
        return studentRepository.findStudentsByQuery(query);
    }

//    @Transactional
//    public Collection<Enrolment> getEnrolmentByStudent(Student stu){
//        return stu.getEnrolments();
//    }

    @Transactional
    public Collection<Enrolment> getEnrolmentByStudentAndEnrolmentStatus(Student student, String status){
        return getEnrolmentByStudentAndEnrolmentStatus(student, status);
    }

    @Transactional
    public Collection<Course> getAllCourse(){
        return courseRepository.findAll();
    }


    @Transactional
    public Collection<Course> getSearchedCourses(String keyword){
        if(keyword==null){
            return courseRepository.findAll();
        }
        return courseRepository.searchFunction(keyword);
    }

    @Transactional
    public Student getStudentById(int id){
        return studentRepository.getById(id);
    }

    @Transactional
    public Collection<Enrolment> findEnrolmentByStudentAndStatus(int student, String status){
        return enrolmentRepository.findEnrolmentByStudentAndStatus(student,status);
    }

    @Transactional
    public Course getCourseById(int id){
        return courseRepository.getById(id);
    }



}
