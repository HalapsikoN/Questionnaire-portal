package by.soft.testProject.questionnairePortal.dto.response;

import by.soft.testProject.questionnairePortal.entity.Field;
import lombok.Data;

import java.util.List;

@Data
public class AnswerResponse {

    private Long id;
    private String label;
    private List<String> responses;

    public AnswerResponse(Field field) {
        id = field.getId();
        label = field.getLabel();
        responses = field.getResponses();
    }
}
