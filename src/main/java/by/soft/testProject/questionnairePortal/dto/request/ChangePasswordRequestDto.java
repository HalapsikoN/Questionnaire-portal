package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ChangePasswordRequestDto {

    @NotNull(message = ErrorMessageConstants.INCORRECT_PASSWORD)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PASSWORD, message = ErrorMessageConstants.INCORRECT_PASSWORD)
    private String currentPassword;

    @NotNull(message = ErrorMessageConstants.INCORRECT_PASSWORD)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PASSWORD, message = ErrorMessageConstants.INCORRECT_PASSWORD)
    private String password;
}
