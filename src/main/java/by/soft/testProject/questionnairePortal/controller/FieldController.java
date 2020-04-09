package by.soft.testProject.questionnairePortal.controller;

import by.soft.testProject.questionnairePortal.dto.request.FieldRequestDto;
import by.soft.testProject.questionnairePortal.dto.response.FieldResponseDto;
import by.soft.testProject.questionnairePortal.entity.Field;
import by.soft.testProject.questionnairePortal.entity.User;
import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;
import by.soft.testProject.questionnairePortal.exception.ServiceException;
import by.soft.testProject.questionnairePortal.service.FieldService;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/fields")
public class FieldController {

    private static final String AUTHORIZATION = "Authorization";

    private FieldService fieldService;
    private UserService userService;

    @Autowired
    public FieldController(FieldService fieldService, UserService userService) {
        this.fieldService = fieldService;
        this.userService = userService;
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addField(@RequestBody FieldRequestDto requestDto) {

        User user = userService.getById(requestDto.getUserId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Field field = requestDto.getFieldForUser(user);

        FieldResponseDto responseDto = new FieldResponseDto(fieldService.add(field));

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllFields() {

        List<Field> fieldList = fieldService.getAll();

        if (fieldList == null || fieldList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FieldResponseDto> fieldDtoList = new ArrayList<>();
        for (Field field : fieldList) {
            fieldDtoList.add(new FieldResponseDto(field));
        }

        return new ResponseEntity<>(fieldDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getField(@PathVariable("id") Field field) {

        if (field == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        FieldResponseDto responseDto = new FieldResponseDto(field);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserFields(@PathVariable("id") Long userId) {

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Field> fieldList = fieldService.getByUserId(userId);

        if (fieldList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FieldResponseDto> responseDto = new ArrayList<>();
        for (Field field : fieldList) {
            responseDto.add(new FieldResponseDto(field));
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateField(@PathVariable("id") Long id, @RequestBody FieldRequestDto requestDto) {

        User user = userService.getById(requestDto.getUserId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Field field = requestDto.getFieldForUser(user);

        FieldResponseDto responseDto;
        try {
            responseDto = new FieldResponseDto(fieldService.update(id, field));
        } catch (ServiceException e) {
            throw new GeneralControllerException(e);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteField(@PathVariable("id") Long id) {

        try {
            fieldService.delete(id);
        } catch (ServiceException e) {
            throw new GeneralControllerException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
