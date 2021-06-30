package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.repo.CourseRepository;
import com.nus.lighthouse.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LecturerService {
    public final LecturerRepository lecturerRepository;
    public final CourseRepository courseRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
    }

    public Collection<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public Collection<Lecturer> getLecturersByQuery(String query) {
        return lecturerRepository.findLecturersByQuery(query);
    }
    
    public Collection<Course> getlectTimetableDetails(Integer id){
        return courseRepository.getlectTimetableDetails(id);
}
}
