package by.soft.testProject.questionnairePortal.service.validator;

import by.soft.testProject.questionnairePortal.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {

    private final static Pattern patternEmail = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private final static Pattern patternPassword = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}");
    private final static Pattern patternPhone = Pattern.compile("^([+]?[0-9]{3,25}([\\s)]+)?)$");

    public static boolean isValidUser(User user) {
        return isValidEmail(user) && isValidPassword(user) && isValidFirstName(user) && isValidLastName(user) && isValidPhone(user);
    }

    public static boolean isValidFirstName(User user) {

        if (user == null) {
            return false;
        }

        String username = user.getFirstName();

        return username != null && !username.isEmpty();
    }

    public static boolean isValidLastName(User user) {

        if (user == null) {
            return false;
        }

        String username = user.getLastName();

        return username != null && !username.isEmpty();
    }

    public static boolean isValidEmail(User user) {

        if (user == null) {
            return false;
        }

        String email = user.getEmail();

        if (email == null || email.isEmpty()) {
            return false;
        }

        Matcher emailMatcher = patternEmail.matcher(email);

        return emailMatcher.matches();

    }

    public static boolean isValidPassword(User user) {

        if (user == null) {
            return false;
        }

        String password = user.getPassword();

        if (password == null || password.isEmpty()) {
            return false;
        }

        Matcher passwordMatcher = patternPassword.matcher(password);

        return passwordMatcher.matches();
    }

    public static boolean isValidPhone(User user) {

        if (user == null) {
            return false;
        }

        String phone = user.getPhone();

        if (phone == null || phone.isEmpty()) {
            return false;
        }

        Matcher passwordMatcher = patternPhone.matcher(phone);

        return passwordMatcher.matches();
    }
}
