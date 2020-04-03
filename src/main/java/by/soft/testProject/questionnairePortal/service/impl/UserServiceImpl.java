package by.soft.testProject.questionnairePortal.service.impl;

import by.soft.testProject.questionnairePortal.entity.Role;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.ServiceException;
import by.soft.testProject.questionnairePortal.repository.RoleRepository;
import by.soft.testProject.questionnairePortal.repository.UserRepository;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public User register(User user, String role) throws ServiceException {
        refactorUser(user, role);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ServiceException("Email has already in use");
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new ServiceException("Phone has already in use");
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateById(Long id, User user, String roleName) throws ServiceException {

        User updateUser = userRepository.findById(id).orElse(null);

        if (updateUser == null) {
            throw new ServiceException("No user with (" + id + ") id");
        }

        refactorUser(user, roleName);

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new ServiceException("Email is already in use");
        }
        if (userRepository.existsByPhoneAndIdNot(user.getPhone(), id)) {
            throw new ServiceException("Phone is already in use");
        }

        user.setId(id);

        return userRepository.save(user);
    }

    @Override
    public User updateInfoById(Long id, User user) throws ServiceException {
        User updateUser = userRepository.findById(id).orElse(null);

        if (updateUser == null) {
            throw new ServiceException("No user with (" + id + ") id");
        }

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new ServiceException("Email is already in use");
        }
        if (userRepository.existsByPhoneAndIdNot(user.getPhone(), id)) {
            throw new ServiceException("Phone is already in use");
        }

        updateUser.setEmail(user.getEmail());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPhone(user.getPhone());

        return userRepository.save(updateUser);
    }

    @Override
    public User updatePasswordById(Long id, String oldPassword, String newPassword) throws ServiceException {
        User updateUser = userRepository.findById(id).orElse(null);

        if (updateUser == null) {
            throw new ServiceException("No user with (" + id + ") id");
        }

        if (!passwordEncoder.matches(oldPassword, updateUser.getPassword())) {
            throw new ServiceException("Incorrect password");
        }

        String newEncodedPassword = passwordEncoder.encode(newPassword);

        updateUser.setPassword(newEncodedPassword);

        return userRepository.save(updateUser);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (!userRepository.existsById(id)) {
            throw new ServiceException("No such user");
        }
        userRepository.deleteById(id);
    }

    private void refactorUser(User user, String roleName) throws ServiceException {

        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            throw new ServiceException("Incorrect role");
        }

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
    }
}
