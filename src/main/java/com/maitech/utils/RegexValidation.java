package com.maitech.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexValidation {

    private static final String PHONE_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    private static final String USERNAME_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$"; //Username must contain at least one letter, at least one number, and be longer than six characters.

    private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,50})"; //Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}