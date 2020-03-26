package by.soft.testProject.questionnairePortal.service.impl;

import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.repository.RoleRepository;
import by.soft.testProject.questionnairePortal.repository.UserRepository;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
