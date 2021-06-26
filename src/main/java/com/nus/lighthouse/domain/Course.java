package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private int examDuration;
    private LocalDate startDate;
    private LocalDate enrollBy;
    private LocalDate examDate;
    private LocalTime startTime;
    private LocalTime examStartTime;

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

    public Course(String courseName, String courseDes, int credits, int maxCap, int duration, int examDuration,
			LocalDate startDate, LocalDate enrollBy, LocalDate examDate, LocalTime startTime, LocalTime examStartTime) {
		super();
		this.courseName = courseName;
		this.courseDes = courseDes;
		this.credits = credits;
		this.maxCap = maxCap;
		this.duration = duration;
		this.examDuration = examDuration;
		this.startDate = startDate;
		this.enrollBy = enrollBy;
		this.examDate = examDate;
		this.startTime = startTime;
		this.examStartTime = examStartTime;
	}

	public Course() {
    }

    public int getCurrCap() {

        return currCap;
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
    
    public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(LocalTime examStartTime) {
		this.examStartTime = examStartTime;
	}
	
	@Override
	public String toString() {
		return "Course [Id=" + Id + ", courseName=" + courseName + ", courseDes=" + courseDes + ", credits=" + credits
				+ ", maxCap=" + maxCap + ", duration=" + duration + ", examDuration=" + examDuration + ", startDate="
				+ startDate + ", enrollBy=" + enrollBy + ", examDate=" + examDate + ", startTime=" + startTime
				+ ", examStartTime=" + examStartTime + "]";
	}
}
