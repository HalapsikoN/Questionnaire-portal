package by.soft.testProject.questionnairePortal.controller;

import by.soft.testProject.questionnairePortal.dto.response.ErrorMessageDto;
import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;
import by.soft.testProject.questionnairePortal.exception.controllerExceptionExtn.EmptyDataExceptionGeneral;
import by.soft.testProject.questionnairePortal.exception.controllerExceptionExtn.IncorrectDataInputExceptionGeneral;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorMessageDto> handle(final BadCredentialsException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = GeneralControllerException.class)
    public ResponseEntity<ErrorMessageDto> handle(final GeneralControllerException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyDataExceptionGeneral.class)
    public ResponseEntity<ErrorMessageDto> handle(final EmptyDataExceptionGeneral exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IncorrectDataInputExceptionGeneral.class)
    public ResponseEntity<ErrorMessageDto> handle(final IncorrectDataInputExceptionGeneral exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDto message = new ErrorMessageDto(ex);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDto message = new ErrorMessageDto(ex);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
