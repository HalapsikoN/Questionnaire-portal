package by.soft.testProject.questionnairePortal.exception;

import javax.security.sasl.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String detail) {
        super(detail);
    }

    public JwtAuthenticationException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
