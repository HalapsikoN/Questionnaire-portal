package by.soft.testProject.questionnairePortal.dto.response;

import by.soft.testProject.questionnairePortal.entity.User;
import lombok.Data;

@Data
public class AuthenticationResponseDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String token;

    public AuthenticationResponseDto(User user, String token) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.token = token;
    }
}
