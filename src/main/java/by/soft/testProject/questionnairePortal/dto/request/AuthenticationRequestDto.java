package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDto {

    @Email(message = ErrorMessageConstants.INCORRECT_EMAIL)
    @NotNull(message = ErrorMessageConstants.INCORRECT_EMAIL)
    private String email;

    @NotNull(message = ErrorMessageConstants.INCORRECT_PASSWORD)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PASSWORD, message = ErrorMessageConstants.INCORRECT_PASSWORD)
    private String password;
}
