package by.soft.testProject.questionnairePortal.repository;

import by.soft.testProject.questionnairePortal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
