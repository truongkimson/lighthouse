package domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrolment {
    public Enrolment() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolmentId;
    private int courseId;

    private String grade;
    private LocalDate registeredDate;
    private String enrolmentStatus;

    @ManyToOne
    private Student student;

    public int getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(int enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + enrolmentId +
                ", courseId=" + courseId +
                ", grade='" + grade + '\'' +
                ", registeredDate=" + registeredDate +
                ", enrolmentStatus='" + enrolmentStatus + '\'' +
                ", student=" + student +
                '}';
    }
}
