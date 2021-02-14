package com.epam.jwd.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void checkLogin() {
        String login = "asdfghjkl";
        boolean isValid = Validator.checkLogin(login);
        assertTrue(isValid);
    }
    @Test
    void checkFalseLogin() {
        String login = "asdf#asdf";
        boolean isValid = Validator.checkLogin(login);
        assertFalse(isValid);
    }

    @Test
    void checkFalseSpaceLogin() {
        String login = "asdfg hjkl";
        boolean isValid = Validator.checkLogin(login);
        assertFalse(isValid);
    }

    @Test
    void checkPassword() {
        String password = "password";
        boolean isValid = Validator.checkPassword(password);
        assertTrue(isValid);
    }

    @Test
    void checkFalsePassword() {
        String password = "pass";
        boolean isValid = Validator.checkPassword(password);
        assertFalse(isValid);
    }

    @Test
    void checkEmail() {
        String email = "m.aaa@gmail.com";
        boolean isValid = Validator.checkEmail(email);
        assertTrue(isValid);
    }

    @Test
    void checkFalseEmail() {
        String email = "m.aaa";
        boolean isValid = Validator.checkEmail(email);
        assertFalse(isValid);
    }

    @Test
    void checkFalseSpaceEmail() {
        String email = "m.a aa";
        boolean isValid = Validator.checkEmail(email);
        assertFalse(isValid);
    }
}