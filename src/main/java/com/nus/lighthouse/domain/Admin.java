package com.nus.lighthouse.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Admin extends User {
    public Admin() {
    }

    public Admin(int id,String email, String password, String firstName, String lastName) {
        super(id,email, password, firstName, lastName);
    }
}
