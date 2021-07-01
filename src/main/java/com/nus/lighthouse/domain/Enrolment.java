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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Course course;

    public Enrolment() {
    }

    public Enrolment(Student student, Course course, LocalDate registeredDate) {
        this.registeredDate = registeredDate;
        this.student = student;
        this.course = course;
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

    public Enrolment(int id, String grade, LocalDate registeredDate, String enrolmentStatus, Student student,
                     Course course) {
        super();
        Id = id;
        this.grade = grade;
        this.registeredDate = registeredDate;
        this.enrolmentStatus = enrolmentStatus;
        this.student = student;
        this.course = course;
    }

    public void setId(int id) {
        Id = id;
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

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}
