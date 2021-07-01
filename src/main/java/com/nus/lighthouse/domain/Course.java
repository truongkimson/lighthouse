package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    private int examDuration;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDate;
    private LocalTime startTime;
    private LocalTime examStartTime;




    @Transient
    private int currCap;

    @ManyToOne
    @JsonBackReference
    private Lecturer lecturer;

    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
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


    public int getCurrCap() {
        if (enrolments != null)
            return enrolments.size();
        return 0;
    }



}
