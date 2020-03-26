package by.soft.testProject.questionnairePortal.repository;

import by.soft.testProject.questionnairePortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

}
