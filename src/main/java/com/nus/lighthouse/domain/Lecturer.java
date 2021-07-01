package com.nus.lighthouse.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@DiscriminatorValue("LEC")
public class Lecturer extends User{
    @NotBlank
    private String designation;

    // some random comments

    @OneToMany(mappedBy = "lecturer")
    @JsonManagedReference
    private Collection<Course> teachCourses;

    public Lecturer() {
    }

    public Lecturer(String email, String password, String firstName, String lastName, String designation) {
        super(email, password, firstName, lastName);
        this.designation = designation;
        this.setRole("ROLE_LEC");
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

    @PreRemove
    public void unlinkTeachingCourses() {
        for (Course c : teachCourses) {
            c.setLecturer(null);
        }

    }
}
