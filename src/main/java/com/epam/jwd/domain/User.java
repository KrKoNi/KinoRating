package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private final int id;
    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String email;
    private final Role role;
    private final LocalDate registrationDate;

    private final Map<Integer, Byte> rates = new HashMap<>();


    public void addRate(Show show, Byte rate) {
        rates.put(show.getId(), rate);
    }

    public void addRates(Map<Integer, Byte> rates) {
        this.rates.putAll(rates);
    }

    User(int id, String login, String password, String firstName, String lastName, LocalDate birthDate, LocalDate registrationDate, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.email = email;
        this.role = role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id;
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String email;
        private Role role;
        private LocalDate registrationDate;

        UserBuilder() {
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public User build() {
            return new User(id, login, password, firstName, lastName, birthDate, registrationDate, email, role);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", login=" + this.login + ", password=" + this.password + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", birthDate=" + this.birthDate + ", email=" + this.email + ", role=" + this.role + ", registrationDate=" + this.registrationDate + ");";
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Map<Integer, Byte> getRates() {
        return rates;
    }
}
