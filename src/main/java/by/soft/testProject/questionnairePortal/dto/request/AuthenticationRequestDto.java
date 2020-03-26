package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn.EmptyDataException;
import by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn.IncorrectDataInputException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private final static Gson gson = new Gson();

    private String email;
    private String password;

    public static AuthenticationRequestDto fromJson(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new EmptyDataException();
        }
        try {
            return gson.fromJson(jsonString, AuthenticationRequestDto.class);
        } catch (JsonSyntaxException e) {
            throw new IncorrectDataInputException("Incorrect JSON object");
        }

    }
}
