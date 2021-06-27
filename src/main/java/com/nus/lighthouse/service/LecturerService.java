package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
