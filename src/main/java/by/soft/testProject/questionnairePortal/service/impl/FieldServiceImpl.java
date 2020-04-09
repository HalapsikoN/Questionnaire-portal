package by.soft.testProject.questionnairePortal.service.impl;

import by.soft.testProject.questionnairePortal.entity.Field;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ServiceException;
import by.soft.testProject.questionnairePortal.repository.FieldRepository;
import by.soft.testProject.questionnairePortal.service.FieldService;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    private FieldRepository fieldRepository;
    private UserService userService;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, UserService userService) {
        this.fieldRepository = fieldRepository;
        this.userService = userService;
    }

    @Override
    public Field add(Field field) {
        removeResponse(field.getUser().getId());
        return fieldRepository.save(field);
    }

    @Override
    public Field addResponse(Long id, String value) throws ServiceException {
        Field field = fieldRepository.getOne(id);
        if (field == null) {
            throw new ServiceException("no field");
        }
        field.getResponses().add(value);
        return fieldRepository.save(field);
    }

    @Override
    public Field getById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    @Override
    public List<Field> getAll() {
        return fieldRepository.findAll();
    }

    @Override
    public List<Field> getByUserId(Long userId) {
        return fieldRepository.getByUserId(userId);
    }

    @Override
    public Field update(Long id, Field field) throws ServiceException {
        Field updateField = fieldRepository.getOne(id);

        if (updateField == null) {
            throw new ServiceException("No field with (" + id + ") id");
        }

        updateField.setLabel(field.getLabel());
        updateField.setType(field.getType());
        updateField.setRequired(field.isRequired());
        updateField.setActive(field.isActive());
        updateField.setOptions(field.getOptions());

        removeResponse(field.getUser().getId());

        return fieldRepository.save(updateField);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (!fieldRepository.existsById(id)) {
            throw new ServiceException("No such field");
        }

        Long userId = fieldRepository.getOne(id).getUser().getId();

        fieldRepository.deleteById(id);

        removeResponse(userId);
    }

    private void removeResponse(Long userId) {

        User user = userService.getById(userId);

        for (Field field : user.getFields()) {

            field.setResponses(new ArrayList<>());

            fieldRepository.save(field);
        }

    }
}
