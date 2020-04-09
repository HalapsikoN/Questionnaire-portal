package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AnswerRequest {

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIELD_RESPONSE)
    private List<FieldResponseRequestDto> answer;
}
