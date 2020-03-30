package by.soft.testProject.questionnairePortal.dto.response;

import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Data
public class ErrorMessageDto {
    private String error;

    public ErrorMessageDto(Throwable exception) {
        this.error = exception.getMessage();
    }

    public ErrorMessageDto(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

        System.out.println(fieldErrorList);

        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError fieldError : fieldErrorList) {
            stringBuilder.append(fieldError.getDefaultMessage()).append("; ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);

        this.error = stringBuilder.toString();
    }
}
