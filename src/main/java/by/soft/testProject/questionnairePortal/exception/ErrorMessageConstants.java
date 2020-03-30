package by.soft.testProject.questionnairePortal.exception;

public class ErrorMessageConstants {

    public final static String INCORRECT_EMAIL = "Incorrect email";
    public final static String INCORRECT_PASSWORD = "Incorrect password";
    public final static String INCORRECT_FIRST_NAME = "Incorrect firstName";
    public final static String INCORRECT_LAST_NAME = "Incorrect lastName";
    public final static String INCORRECT_PHONE = "Incorrect phone";
    public final static String INCORRECT_ROLE = "Incorrect role";

    public final static String REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}";
    public final static String REGEX_PHONE = "^([+]?[0-9]{3,25}([\\s)]+)?)$";
}
