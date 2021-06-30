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
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;
    public final EnrolmentRepository enrolmentRepository;
    public final CourseRepository courseRepository;
    public final UserService userService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, EnrolmentRepository enrolmentRepository,
                              CourseRepository courseRepository, UserServiceImpl userService) {
        this.studentRepository = studentRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

  
  
    @Transactional
    public Collection<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    @Transactional
    public Collection<Student> getStudentsByQuery(String query) {
        return studentRepository.findStudentsByQuery(query);
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

//    @Transactional
//    public Collection<Enrolment> getEnrolmentByStudentAndEnrolmentStatus(Student student, String status){
//        return getEnrolmentByStudentAndEnrolmentStatus(student, status);
//    }

//    @Transactional
//    public Collection<Course> getAllCourse(){
//        return courseRepository.findAll();
//    }


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
    public Collection<Enrolment> findEnrolmentByStudentAndStatusAndCourse(int student, String status, int course){
        return enrolmentRepository.findEnrolmentByStudentAndStatusAndCourse(student,status, course);
    }

    @Transactional
    public Course getCourseById(int id){
        return courseRepository.getById(id);
    }

    @Transactional
    public Enrolment createEnrolment(Enrolment enrolment){
        return enrolmentRepository.saveAndFlush(enrolment);
    }

    @Transactional
    public boolean ifMaxCapacityExceeded(int courseId){
        Course courses = getCourseById(courseId);
        if(courses.getEnrolments().size()>= courses.getMaxCap()){
            return true;
        }
        else{
            return false;
        }
    }

    @Transactional
    public void deleteEnrolment(int id){
        if (enrolmentRepository.getById(id) !=null){
            enrolmentRepository.delete(enrolmentRepository.getById(id));
        }
    }

}
