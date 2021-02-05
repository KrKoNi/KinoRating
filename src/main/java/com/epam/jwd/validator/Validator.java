package com.epam.jwd.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validator {
    private static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";

    private Validator() {

    }

    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean checkPassword(String password) {
        return password.length() >= 8;
    }

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