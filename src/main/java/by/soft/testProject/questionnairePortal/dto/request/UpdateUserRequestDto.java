package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateUserRequestDto {

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIRST_NAME)
    private String firstName;

    @NotNull(message = ErrorMessageConstants.INCORRECT_LAST_NAME)
    private String lastName;

    @Email(message = ErrorMessageConstants.INCORRECT_EMAIL)
    @NotNull(message = ErrorMessageConstants.INCORRECT_EMAIL)
    private String email;

    @NotNull(message = ErrorMessageConstants.INCORRECT_PHONE)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PHONE, message = ErrorMessageConstants.INCORRECT_PHONE)
    private String phone;

    public User getUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);

        return user;
    }
}
