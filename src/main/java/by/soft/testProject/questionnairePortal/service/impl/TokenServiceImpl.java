package by.soft.testProject.questionnairePortal.service.impl;

import by.soft.testProject.questionnairePortal.config.security.jwt.JwtTokenProvider;
import by.soft.testProject.questionnairePortal.entity.Token;
import by.soft.testProject.questionnairePortal.repository.TokenRepository;
import by.soft.testProject.questionnairePortal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, JwtTokenProvider jwtTokenProvider) {
        this.tokenRepository = tokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String clearTokenFromBearer(String bearerToken) {
        String result = null;

        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            result = bearerToken.substring(7);
        }

        return result;
    }

    @Override
    public Token add(String token) {
        return tokenRepository.save(new Token(token));
    }

    @Override
    public void delete(String string) {
        if (tokenRepository.existsByToken(string)) {
            Token token = tokenRepository.getByToken(string);
            tokenRepository.deleteById(token.getId());
        }
    }

    @Override
    public boolean exists(String token) {
        return tokenRepository.existsByToken(token);
    }
}
