package by.soft.testProject.questionnairePortal.controller;

import by.soft.testProject.questionnairePortal.dto.request.AnswerRequest;
import by.soft.testProject.questionnairePortal.dto.request.FieldResponseRequestDto;
import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;
import by.soft.testProject.questionnairePortal.exception.ServiceException;
import by.soft.testProject.questionnairePortal.service.FieldService;
import by.soft.testProject.questionnairePortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/responses")
public class ResponseController {

    private UserService userService;
    private FieldService fieldService;

    @Autowired
    public ResponseController(UserService userService, FieldService fieldService) {
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @PostMapping
    public ResponseEntity<?> addResponse(@RequestBody AnswerRequest requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (FieldResponseRequestDto fieldResponseRequestDto : requestDto.getAnswer()) {

            try {
                fieldService.addResponse(fieldResponseRequestDto.getFieldId(), fieldResponseRequestDto.getValue());
            } catch (ServiceException e) {
                throw new GeneralControllerException(e);
            }

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
