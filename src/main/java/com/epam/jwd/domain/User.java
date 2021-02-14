package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * User entity.
 */
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

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param login    the login
     * @param password the password
     */
    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets registration date.
     *
     * @return the registration date
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets registration date.
     *
     * @param registrationDate the registration date
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Gets rates.
     *
     * @return the rates
     */
    public Map<Integer, Byte> getRates() {
        return rates;
    }

    /**
     * Add rate.
     *
     * @param show the show
     * @param rate the rate
     */
    public void addRate(Show show, Byte rate) {
        rates.put(show.getId(), rate);
    }

    /**
     * Add rates.
     *
     * @param rates the rates
     */
    public void addRates(Map<Integer, Byte> rates) {
        this.rates.putAll(rates);
    }

}
