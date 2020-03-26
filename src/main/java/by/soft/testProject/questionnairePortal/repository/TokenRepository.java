package by.soft.testProject.questionnairePortal.repository;

import by.soft.testProject.questionnairePortal.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token getByToken(String token);
    boolean existsByToken(String token);
}
