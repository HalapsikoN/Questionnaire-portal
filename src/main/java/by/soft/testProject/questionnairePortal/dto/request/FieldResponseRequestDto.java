package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FieldResponseRequestDto {

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIELD_ID)
    private Long fieldId;

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIELD_LABEL)
    private String label;

    @NotNull(message = ErrorMessageConstants.INCORRECT_FIELD_VALUE)
    private String value;

}
