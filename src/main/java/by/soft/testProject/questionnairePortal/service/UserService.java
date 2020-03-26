package by.soft.testProject.questionnairePortal.service;

import by.soft.testProject.questionnairePortal.entity.User;

public interface UserService {

    User getByEmail(String email);
}
