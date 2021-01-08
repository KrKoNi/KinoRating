package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Serializable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String login;
    private final String email;
    private final String password;
    private final LocalDate registrationDate;
    private final Role role;
    private final LocalDateTime lastLogin = LocalDateTime.now();

    public User(int id, String login, String password, String firstName, String lastName, LocalDate birthDate, String email, LocalDate registrationDate, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
