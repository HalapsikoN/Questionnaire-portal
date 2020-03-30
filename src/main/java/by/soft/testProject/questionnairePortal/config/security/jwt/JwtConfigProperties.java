package by.soft.testProject.questionnairePortal.config.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwtconfigprops.properties")
@ConfigurationProperties(prefix = "jwt.token")
@Data
public class JwtConfigProperties {

    private String secret;
    private String expired;
}
