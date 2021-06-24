package com.nus.lighthouse.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@DiscriminatorValue("LEC")
public class Lecturer extends User{
    private String designation;

    @OneToMany(mappedBy = "lecturer")
    private Collection<Course> teachCourses;

    public Lecturer() {
    }

    public Lecturer(String email, String password, String firstName, String lastName, String designation) {
        super(email, password, firstName, lastName);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Collection<Course> getTeachCourses() {
        return teachCourses;
    }

    public void setTeachCourses(Collection<Course> teachCourses) {
        this.teachCourses = teachCourses;
    }
}