package by.soft.testProject.questionnairePortal.dto.response;

import by.soft.testProject.questionnairePortal.entity.Field;
import lombok.Data;

import java.util.List;

@Data
public class FieldResponseDto {

    private Long id;
    private String label;
    private String type;
    private boolean required;
    private boolean active;
    private List<String> options;

    public FieldResponseDto(Field field) {

        this.id = field.getId();
        this.label = field.getLabel();
        this.type = field.getType().name();
        this.required = field.isRequired();
        this.active = field.isActive();
        this.options = field.getOptions();
    }
}
