package by.soft.testProject.questionnairePortal.service;

import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ServiceException;

import java.util.List;

public interface UserService {

    User register(User user, String role) throws ServiceException;

    List<User> getAll();
    User getByEmail(String email);
    User getById(Long id);

    User updateById(Long id, User user, String roleName) throws ServiceException;
    User updateInfoById(Long id, User user) throws ServiceException;
    User updatePasswordById(Long id, String oldPassword, String newPassword) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
