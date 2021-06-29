package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    //private int courseId;

    private String grade;
    private LocalDate registeredDate;
    private String enrolmentStatus;

    @ManyToOne
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JsonBackReference
    private Course course;

    public Enrolment() {
    }

    public Enrolment(LocalDate registeredDate, String enrolmentStatus, Student student, Course course) {
        this.registeredDate = registeredDate;
        this.enrolmentStatus = enrolmentStatus;
        this.student = student;
        this.course = course;
    }

    public int getId() {
        return Id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getEnrolmentStatus() {
        return enrolmentStatus;
    }

    public void setEnrolmentStatus(String enrolmentStatus) {
        this.enrolmentStatus = enrolmentStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + Id +
                ", grade='" + grade + '\'' +
                ", registeredDate=" + registeredDate +
                ", enrolmentStatus='" + enrolmentStatus + '\'' +
                ", student=" + student +
                '}';
    }
}
