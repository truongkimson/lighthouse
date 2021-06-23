package com.nus.lighthouse.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LEC")
public class Lecturer extends User{
    private String designation;

    public Lecturer() {
    }

    public Lecturer(String email, String password, String firstName, String lastName, String designation) {
        super(email, password, firstName, lastName);
        this.designation = designation;
    }
}
