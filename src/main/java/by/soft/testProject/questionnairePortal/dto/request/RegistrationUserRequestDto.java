package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationUserRequestDto {

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIRST_NAME)
    private String firstName;

    @NotNull(message = ErrorMessageConstants.INCORRECT_LAST_NAME)
    private String lastName;

    @NotNull(message = ErrorMessageConstants.INCORRECT_PASSWORD)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PASSWORD, message = ErrorMessageConstants.INCORRECT_PASSWORD)
    private String password;

    @Email(message = ErrorMessageConstants.INCORRECT_EMAIL)
    @NotNull(message = ErrorMessageConstants.INCORRECT_EMAIL)
    private String email;

    @NotNull(message = ErrorMessageConstants.INCORRECT_PHONE)
    @Pattern(regexp = ErrorMessageConstants.REGEX_PHONE, message = ErrorMessageConstants.INCORRECT_PHONE)
    private String phone;

    @NotNull(message = ErrorMessageConstants.INCORRECT_ROLE)
    private String role;

    public User getUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        return user;
    }

    public String getRole() {
        return role.toUpperCase();
    }
}
