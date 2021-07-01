package com.nus.lighthouse.domain;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_email",columnNames = {"email"})})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Email
    @NotBlank
    private String email;
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String role;

    public User() {
    }

    public User(int Id,String email, String password, String firstName, String lastName) {
    	this.Id=Id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() { return firstName + " " + lastName; }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
