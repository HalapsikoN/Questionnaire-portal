package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn.EmptyDataException;
import by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn.IncorrectDataInputException;
import by.soft.testProject.questionnairePortal.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

@Data
public class RegistrationUserRequestDto {

    private final static Gson gson = new Gson();

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String role;

    public User getUser(){
        User user=new User();
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

    public static RegistrationUserRequestDto fromJson(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new EmptyDataException();
        }
        try {
            return gson.fromJson(jsonString, RegistrationUserRequestDto.class);
        } catch (JsonSyntaxException e) {
            throw new IncorrectDataInputException("Incorrect JSON object");
        }

    }
}
