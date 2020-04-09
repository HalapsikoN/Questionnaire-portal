package by.soft.testProject.questionnairePortal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fields")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Field extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "label", nullable = false, length = 100)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 100)
    private FieldType type;

    @Column(name = "required", nullable = false, length = 10)
    private boolean required;

    @Column(name = "active", nullable = false, length = 10)
    private boolean active;

    @ElementCollection
    @Column(name = "options", nullable = true)
    private List<String> options;


    @ElementCollection
    @Column(name = "responses", nullable = true)
    private List<String> responses;
}
