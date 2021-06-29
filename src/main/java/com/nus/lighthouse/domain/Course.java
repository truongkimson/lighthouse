package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotBlank
    private String courseName;
    @NotBlank
    private String courseDes;
    @Min(1)
    private int credits;
    @Min(1)
    private int maxCap;
    @Min(1)
    private int duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate enrollBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate examDate;

    @Transient
    private int currCap;

    @ManyToOne
    @JsonBackReference
    private Lecturer lecturer;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private Collection<Enrolment> enrolments;


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

    public Course() {
    }

    public int getCurrCap() {
        if (enrolments==null){
            return 0;
        }
        else{
            return enrolments.size();
        }
    }

    public void setCurrCap(int currCap) {
        this.currCap = currCap;
    }

    public int getId() {
        return Id;
    }

    public void setId(int courseId) {
        this.Id = courseId;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + Id +
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
