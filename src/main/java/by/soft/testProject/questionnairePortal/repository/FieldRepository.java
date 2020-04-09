package by.soft.testProject.questionnairePortal.repository;

import by.soft.testProject.questionnairePortal.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> getByUserId(Long userId);

}
