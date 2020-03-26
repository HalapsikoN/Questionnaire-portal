package by.soft.testProject.questionnairePortal.controller;

import by.soft.testProject.questionnairePortal.config.security.jwt.JwtTokenProvider;
import by.soft.testProject.questionnairePortal.dto.request.AuthenticationRequestDto;
import by.soft.testProject.questionnairePortal.dto.request.RegistrationUserRequestDto;
import by.soft.testProject.questionnairePortal.dto.response.AuthenticationResponseDto;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.service.ServiceException;
import by.soft.testProject.questionnairePortal.service.TokenService;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TokenService tokenService;
    private final HttpHeaders httpHeaders;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.tokenService = tokenService;
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    @PostMapping("logIn")
    public ResponseEntity<?> logInUser(@RequestBody String request) {
        try {

            AuthenticationRequestDto requestDto = AuthenticationRequestDto.fromJson(request);

            String email = requestDto.getEmail();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

            User user = userService.getByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with username " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            tokenService.add(token);

            AuthenticationResponseDto responseDto = new AuthenticationResponseDto(user, token);

            return new ResponseEntity<>(responseDto, httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("logUp")
    public ResponseEntity<?> registerUser(@RequestBody String request) {

        RegistrationUserRequestDto requestDto = RegistrationUserRequestDto.fromJson(request);

        if (requestDto == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        User user = requestDto.getUser();
        String role = requestDto.getRole();

        User registeredUser;
        try {
            registeredUser = userService.register(user, role);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        String token = jwtTokenProvider.createToken(registeredUser.getEmail(), registeredUser.getRoles());

        tokenService.add(token);

        AuthenticationResponseDto responseDto = new AuthenticationResponseDto(registeredUser, token);

        return new ResponseEntity<>(responseDto, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("logOut")
    public ResponseEntity<?> logOutUser(@RequestHeader("Authorization") String bearerToken) {

        String token = tokenService.clearTokenFromBearer(bearerToken);

        tokenService.delete(token);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
