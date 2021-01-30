package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private Role role;
    private LocalDate registrationDate;

    private final Map<Integer, Byte> rates = new HashMap<>();

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }
    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Map<Integer, Byte> getRates() {
        return rates;
    }



    public void addRate(Show show, Byte rate) {
        rates.put(show.getId(), rate);
    }

    public void addRates(Map<Integer, Byte> rates) {
        this.rates.putAll(rates);
    }

}
