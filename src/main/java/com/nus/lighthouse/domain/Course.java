package com.nus.lighthouse.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String courseDes;
    private int credits;
    private int maxCap;
    private int duration;
    private LocalDate startDate;
    private LocalDate enrolBy;
    private LocalDate examDate;

    @ManyToOne
    private Lecturer lecturer;

    @OneToMany(mappedBy = "course")
    private Collection<Enrolment> enrolment;

    public Course() {
    }

    public Course(int courseId, String courseName, String courseDes, int credits, int maxCap, int duration, LocalDate startDate, LocalDate enrolBy, LocalDate examDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.credits = credits;
        this.maxCap = maxCap;
        this.duration = duration;
        this.startDate = startDate;
        this.enrolBy = enrolBy;
        this.examDate = examDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEnrolBy() {
        return enrolBy;
    }

    public void setEnrolBy(LocalDate enrolBy) {
        this.enrolBy = enrolBy;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDes='" + courseDes + '\'' +
                ", credits=" + credits +
                ", maxCap=" + maxCap +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", enrolBy=" + enrolBy +
                ", examDate=" + examDate +
                '}';
    }
}
