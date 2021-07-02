package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;
import com.nus.lighthouse.exception.LecturerNotFoundException;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class LecturerServiceImpl implements LecturerService {
    public final LecturerRepository lecturerRepository;
    public final UserService userService;
    public final CourseRepository courseRepository;
    

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, UserServiceImpl userService,
    		CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.userService = userService;
        this.courseRepository = courseRepository; 
    }


    public Collection<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public Collection<Lecturer> getLecturersByQuery(String query) {
        return lecturerRepository.findLecturersByQuery(query);
    }

    public Lecturer getLecturerById(int lecturerId) {
        try {
            return lecturerRepository.findById(lecturerId).orElseThrow();
        }
        catch (NoSuchElementException e) {
            throw new LecturerNotFoundException("Lecturer not found");
        }
    }

    @Transactional
    public void createLecturer(Lecturer lecturer) throws EmailAlreadyExistsException {
        if (userService.checkEmailExists(lecturer.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists in the system");
        }
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void updateLecturer(Lecturer lecturer, int lecturerId) throws EmailAlreadyExistsException {
        if (userService.checkEmailUpdateExists(lecturer.getEmail(), lecturerId)) {
            throw new EmailAlreadyExistsException("Updated email already exists in the system");
        }
        lecturer.setId(lecturerId);
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void deleteLecturerById(int lecturerId) {
        lecturerRepository.deleteById(lecturerId);
    }
    
    @Transactional
    public Collection<Course> getlectTimetableDetails(Integer id){
        return courseRepository.getlectTimetableDetails(id);
        }
}
