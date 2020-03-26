package by.soft.testProject.questionnairePortal.dto.response;

import lombok.Data;

@Data
public class ErrorMessageDto {
    private String error;

    public ErrorMessageDto(Throwable exception) {
        this.error = exception.getMessage();
    }
}
