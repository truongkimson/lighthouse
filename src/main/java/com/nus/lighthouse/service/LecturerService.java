package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class LecturerService {
    public final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
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
    public void createLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void updateLecturer(Lecturer lecturer, int lecturerId) {
        lecturer.setId(lecturerId);
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void deleteLecturerById(int lecturerId) {
        lecturerRepository.deleteById(lecturerId);
    }

}
