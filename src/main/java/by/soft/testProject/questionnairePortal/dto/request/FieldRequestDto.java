package by.soft.testProject.questionnairePortal.dto.request;

import by.soft.testProject.questionnairePortal.entity.Field;
import by.soft.testProject.questionnairePortal.entity.FieldType;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ErrorMessageConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FieldRequestDto {

    @NotNull(message = ErrorMessageConstants.INCORRECT_USER_ID)
    private Long userId;

    @NotNull(message = ErrorMessageConstants.INCORRECT_LABEL)
    private String label;

    @NotNull(message = ErrorMessageConstants.INCORRECT_TYPE)
    private FieldType type;

    @NotNull(message = ErrorMessageConstants.INCORRECT_REQUIRED)
    private boolean required;

    @NotNull(message = ErrorMessageConstants.INCORRECT_ACTIVE)
    private boolean active;

    @NotNull(message = ErrorMessageConstants.INCORRECT_OPTIONS)
    private List<String> options;

    public Long getUserId() {
        return userId;
    }

    public Field getFieldForUser(User user) {
        Field field = new Field();
        field.setLabel(label);
        field.setType(type);
        field.setRequired(required);
        field.setActive(active);
        field.setOptions(options);
        field.setUser(user);

        return field;
    }

}
