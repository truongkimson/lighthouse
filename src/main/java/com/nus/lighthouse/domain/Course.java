package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Course name field cannot be blank")
    private String courseName;
    @NotBlank(message = "Course description cannot be blank")
    private String courseDes;
    @Min(value = 1, message = "Credits value must be greater than 0")
    private int credits;
    @Min(value = 1, message = "Maximum capacity must be greater than 0")
    private int maxCap;
    @Min(value = 1, message = "Duration must be greater than 0")
    private int duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date cannot be empty")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Enroll by date cannot be empty")
    private LocalDate enrollBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Exam date cannot be empty")
    private LocalDate examDate;

    @Transient
    private int currCap;

    @ManyToOne
    @JsonBackReference
    private Lecturer lecturer;

    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<Enrolment> enrolments;

    public Course() {
    }

    public Course(String courseName, String courseDes, int credits, int maxCap, int duration, LocalDate startDate, LocalDate enrollBy, LocalDate examDate) {
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.credits = credits;
        this.maxCap = maxCap;
        this.duration = duration;
        this.startDate = startDate;
        this.enrollBy = enrollBy;
        this.examDate = examDate;
    }


    public void setCurrCap(int currCap) {
        this.currCap = currCap;
    }

    public int getId() {
        return id;
    }

    public void setId(int courseId) {
        this.id = courseId;
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

    public LocalDate getEnrollBy() {
        return enrollBy;
    }

    public void setEnrollBy(LocalDate enrolBy) {
        this.enrollBy = enrolBy;
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

    public Collection<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(Collection<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public int getCurrCap() {
        if (enrolments != null)
            return enrolments.size();
        return 0;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseDes='" + courseDes + '\'' +
                ", credits=" + credits +
                ", maxCap=" + maxCap +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", enrolBy=" + enrollBy +
                ", examDate=" + examDate +
                '}';
    }
}
