package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.Course;
import com.nus.lighthouse.domain.Lecturer;
import com.nus.lighthouse.exception.EmailAlreadyExistsException;

import java.util.Collection;

public interface LecturerService {
    Collection<Lecturer> getAllLecturers();
    Collection<Lecturer> getLecturersByQuery(String query);
    Lecturer getLecturerById(int lecturerId);
    void createLecturer(Lecturer lecturer) throws EmailAlreadyExistsException;
    void updateLecturer(Lecturer lecturer, int lecturerId) throws EmailAlreadyExistsException;
    void deleteLecturerById(int lecturerId);
    Collection<Course> getlectTimetableDetails(Integer id);
}
