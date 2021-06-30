package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class LecturerServiceImpl implements LecturerService {
    public final LecturerRepository lecturerRepository;
    public final UserService userService;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, UserServiceImpl userService) {
        this.lecturerRepository = lecturerRepository;
        this.userService = userService;
    }


    public Collection<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public Collection<Lecturer> getLecturersByQuery(String query) {
        return lecturerRepository.findLecturersByQuery(query);
    }

    public Lecturer getLecturerById(int lecturerId) {
        return lecturerRepository.findById(lecturerId).orElseThrow();
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

}
