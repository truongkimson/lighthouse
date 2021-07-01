package com.nus.lighthouse.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Admin extends User {
    public Admin() {
    }

    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
        this.setRole("ROLE_ADM");
    }
}
