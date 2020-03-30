package by.soft.testProject.questionnairePortal.config.security.jwt;

import by.soft.testProject.questionnairePortal.entity.Role;
import by.soft.testProject.questionnairePortal.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser createUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Role role : userRoles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorityList;
    }
}
