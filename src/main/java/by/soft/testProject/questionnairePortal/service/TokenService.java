package by.soft.testProject.questionnairePortal.service;

import by.soft.testProject.questionnairePortal.entity.Token;

public interface TokenService {

    String clearTokenFromBearer(String bearerToken);

    Token add(String token);

    void delete(String string);

    boolean exists(String token);
}
