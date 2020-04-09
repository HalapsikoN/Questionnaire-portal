package by.soft.testProject.questionnairePortal.service;

import by.soft.testProject.questionnairePortal.entity.Field;
import by.soft.testProject.questionnairePortal.exception.ServiceException;

import java.util.List;

public interface FieldService {

    Field add(Field field);
    Field addResponse(Long id, String value) throws ServiceException;

    Field getById(Long id);
    List<Field> getAll();
    List<Field> getByUserId(Long userId);

    Field update(Long id, Field field) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
