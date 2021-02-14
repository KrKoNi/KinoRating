package com.epam.jwd.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Validator, checks if some parameters matches requirements.
 */
public class Validator {
    private static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{4,19}$";

    private Validator() {

    }

    /**
     * Check user login.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    /**
     * Check password length.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean checkPassword(String password) {
        return password.length() >= 8;
    }

    /**
     * Check email format.
     *
     * @param mail the mail
     * @return the boolean
     */
    public static boolean checkEmail(String mail) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(mail);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}