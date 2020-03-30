package by.soft.testProject.questionnairePortal.config.security.jwt;

import by.soft.testProject.questionnairePortal.exception.JwtAuthenticationException;
import by.soft.testProject.questionnairePortal.service.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final static Logger logger = LogManager.getLogger(JwtTokenFilter.class);

    private JwtTokenProvider jwtTokenProvider;
    private TokenService tokenService;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, TokenService tokenService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (token != null && jwtTokenProvider.isValidToken(token) && tokenService.exists(token)) {

                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                tokenService.delete(token);
            }
        } catch (JwtAuthenticationException e) {
            logger.warn(e);
            tokenService.delete(token);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
