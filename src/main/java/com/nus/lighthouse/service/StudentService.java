package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.EnrolmentRepository;
import com.nus.lighthouse.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class StudentService {
    public final StudentRepository studentRepository;
    public final EnrolmentRepository enrolmentRepository;
    public final CourseRepository courseRepository;
    public final UserService userService;

    @Autowired
    public StudentService(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository,
                          CourseRepository courseRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByQuery(String query) {
        return studentRepository.findStudentsByQuery(query);
    }

    public Collection<Enrolment> getEnrolmentByStudent(Student stu) {

        return stu.getEnrolments();
    }

    public Student getDummyStudent() {
        return studentRepository.getById(1);
    }

    public Collection<Course> getAllCourse() {
        return courseRepository.findAll();
    }

//    public Collection<Course> getSearchedCourses(){
//        String placeholder = "design";
//        return studentRepository.searchFunction(placeholder);
//    }

    public Collection<Course> getSearchedCourses(String keyword) {
        if (keyword == null) {
            return courseRepository.findAll();
        }
        return courseRepository.searchFunction(keyword);
    }

    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Transactional
    public void createStudent(Student student) throws EmailAlreadyExistsException {
        if (userService.checkEmailExists(student.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists in the system");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student student, int studentId) throws EmailAlreadyExistsException {
        if (userService.checkEmailUpdateExists(student.getEmail(), studentId)) {
            throw new EmailAlreadyExistsException("Updated email already exists in the system");
        }

        student.setId(studentId);
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }

    public Collection<Student> getStudentsAvailToEnrolByCourseId(int courseId) {
        Course course = courseRepository.getById(courseId);
        Collection<Enrolment> enrolmentsByCourse = enrolmentRepository.findEnrolmentsByCourse(course);
        Collection<Student> studentsEnrolledInCourse =
                enrolmentsByCourse.stream().map(Enrolment::getStudent).collect(Collectors.toList());
        return studentRepository.findAll().stream().filter(s -> !studentsEnrolledInCourse.contains(s))
                        .collect(Collectors.toList());
    }

    public Collection<Student> getStudentsAvailToEnrolByCourseIdAndQuery(int courseId, String query) {
        Collection<Student> availStudents = getStudentsAvailToEnrolByCourseId(courseId);
        return availStudents.stream().filter(s -> s.getFirstName()
                                                   .contains(query) ||
                s.getLastName().contains(query) || s.getEmail().contains(query)).collect(Collectors.toList());
    }
}

