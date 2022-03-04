package com.tunCode.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, max = 19, message = "First name must be between 2 and 19 characters")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 19, message = "Last name must be between 2 and 19 characters")
    private String lastName;

    @Column(name = "email_id")
    @Email(message = "Email should be valid !")
    private String emailId;

    public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

}
