package com.nus.lighthouse.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String courseName;
    private String courseDes;
    private int credits;
    private int maxCap;
    private int duration;
    private LocalDate startDate;
    private LocalDate enrollBy;
    private LocalDate examDate;

    @ManyToOne
    private Lecturer lecturer;

    @OneToMany(mappedBy = "course")
    private Collection<Enrolment> enrolment;

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

    public Collection<Enrolment> getEnrolment() {
		return enrolment;
	}

	public void setEnrolment(Collection<Enrolment> enrolment) {
		this.enrolment = enrolment;
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
