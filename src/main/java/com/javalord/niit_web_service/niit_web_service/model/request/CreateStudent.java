package com.javalord.niit_web_service.niit_web_service.model.request;

// JPA Validations

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class CreateStudent {
    @NotBlank(message = "First name cannot be blank!")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank!")
    private String lastName;
    @Email(message = "Email must correctly specified!")
    private String email;
    @Size(min = 8, max = 20, message = "Password must be between 8 to 20 characters!")
    @NotBlank(message = "Password cannot be blank!")
    private String password;

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

    @Override
    public String toString() {
        return "CreateStudent{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
