package by.soft.testProject.questionnairePortal.config.security.jwt;

import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(JwtUserDetailsService.class);

    private UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.getByEmail(email);

        if (user == null) {
            logger.warn("No user with ({}) email.", email);
            throw new UsernameNotFoundException("No such user");
        }

        JwtUser jwtUser = JwtUserFactory.createUser(user);

        return jwtUser;
    }
}
