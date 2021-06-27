package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@DiscriminatorValue("STU")
public class Student extends User {
    private String phone;
    private String address;
    private LocalDate dob;
    private LocalDate enrolmentDate;
    @Transient
    private double gpa;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private Collection<Enrolment> enrolments;

    public Student() {
    }

    public Student(int id, String email, String password, String firstName,
                   String lastName, String phone, String address,
                   LocalDate dob, LocalDate enrolmentDate) {
        super(id, email, password, firstName, lastName);
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.enrolmentDate = enrolmentDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(LocalDate enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }

    public Collection<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(Collection<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    //Will have to implement the calculation of the GPA here
    public double getGpa() {
        return gpa;
    }

    public Collection<Enrolment> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(Collection<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}

	@Override
    public String toString() {
        return super.toString() + "Student{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", enrolmentDate=" + enrolmentDate +
                ", gpa=" + gpa +
                ", Enrolment=" + enrolments +
                '}';
    }
}
