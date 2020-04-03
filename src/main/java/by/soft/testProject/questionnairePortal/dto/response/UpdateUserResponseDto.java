package by.soft.testProject.questionnairePortal.dto.response;

import by.soft.testProject.questionnairePortal.entity.User;
import lombok.Data;

@Data
public class UpdateUserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String token;

    public UpdateUserResponseDto(User user, String token) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.token = token;
    }
}
