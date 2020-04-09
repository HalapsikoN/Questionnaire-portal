package by.soft.testProject.questionnairePortal.controller;

import by.soft.testProject.questionnairePortal.dto.request.ChangePasswordRequestDto;
import by.soft.testProject.questionnairePortal.dto.request.UpdateUserRequestDto;
import by.soft.testProject.questionnairePortal.dto.response.AnswerResponse;
import by.soft.testProject.questionnairePortal.dto.response.FieldResponseDto;
import by.soft.testProject.questionnairePortal.dto.response.UpdateUserResponseDto;
import by.soft.testProject.questionnairePortal.dto.response.UserResponseDto;
import by.soft.testProject.questionnairePortal.entity.Field;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;
import by.soft.testProject.questionnairePortal.exception.ServiceException;
import by.soft.testProject.questionnairePortal.service.TokenService;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private static final String AUTHORIZATION = "Authorization";

    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> userList = userService.getAll();

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserResponseDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(new UserResponseDto(user));
        }

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/info/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserById(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable(name = "id") Long id, @RequestBody UpdateUserRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = null;

        try {
            user = userService.updateInfoById(id, requestDto.getUser());
        } catch (ServiceException e) {
            throw new GeneralControllerException(e);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        String token = tokenService.clearTokenFromBearer(bearerToken);

        tokenService.delete(token);

        String newToken = tokenService.createNewAfterUpdate(token, requestDto.getEmail());

        tokenService.add(newToken);

        UpdateUserResponseDto userDto = new UpdateUserResponseDto(user, newToken);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/passwordChange/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "id") Long id, @RequestBody ChangePasswordRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = null;

        try {
            user = userService.updatePasswordById(id, requestDto.getCurrentPassword(), requestDto.getPassword());
        } catch (ServiceException e) {
            throw new GeneralControllerException(e);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") Long id) {

        try {
            userService.delete(id);
        } catch (ServiceException e) {
            throw new GeneralControllerException(e);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("currentUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(AUTHORIZATION) String bearerToken) {

        String email = tokenService.getEmail(bearerToken);

        User user = userService.getByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserResponseDto userDto = new UserResponseDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("fields")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserField(@RequestHeader(AUTHORIZATION) String bearerToken) {

        String email = tokenService.getEmail(bearerToken);

        User user = userService.getByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Field> fieldList = user.getFields();

        List<FieldResponseDto> fieldResponseDtos = new ArrayList<>();
        for (Field field : fieldList) {
            fieldResponseDtos.add(new FieldResponseDto(field));
        }

        return new ResponseEntity<>(fieldResponseDtos, HttpStatus.OK);

    }

    @GetMapping("responses")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserResponses(@RequestHeader(AUTHORIZATION) String bearerToken) {

        String email = tokenService.getEmail(bearerToken);

        User user = userService.getByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Field> fieldList = user.getFields();

        List<AnswerResponse> fieldResponseDto = new ArrayList<>();
        for (Field field : fieldList) {
            fieldResponseDto.add(new AnswerResponse(field));
        }

        return new ResponseEntity<>(fieldResponseDto, HttpStatus.OK);
    }
}
