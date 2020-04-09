package by.soft.testProject.questionnairePortal.config.security.jwt;

import by.soft.testProject.questionnairePortal.entity.Role;
import by.soft.testProject.questionnairePortal.entity.Token;
import by.soft.testProject.questionnairePortal.exception.JwtAuthenticationException;
import by.soft.testProject.questionnairePortal.repository.TokenRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final String ROLES = "roles";
    private static final String AUTHORIZATION = "Authorization";

    @Value("${jwt.token.secret}")
    private String secretWord;

    @Value("${jwt.token.expired}")
    private Long validityInMillisecond;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TokenRepository tokenRepository;

    public String createToken(String email, List<Role> roleList) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put(ROLES, roleList);

        Date nowDate = new Date();
        Date endOfValidityDate = new Date(nowDate.getTime() + validityInMillisecond);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(endOfValidityDate)
                .signWith(SignatureAlgorithm.HS256, secretWord)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        return clearTokenFromBearer(bearerToken);
    }

    public List<Role> getRoles(String token) {
        return (List<Role>) Jwts.parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody()
                .get(ROLES);
    }

    public boolean isValidToken(String token) throws JwtAuthenticationException {

        try {
            boolean result = true;

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                result = false;
                deleteTokenFromBD(token);
            }

            return result;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private void deleteTokenFromBD(String string) {
        if (tokenRepository.existsByToken(string)) {
            Token token = tokenRepository.getByToken(string);
            tokenRepository.deleteById(token.getId());
        }
    }

    private String clearTokenFromBearer(String bearerToken) {
        String result = null;

        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            result = bearerToken.substring(7);
        }

        return result;
    }
}
