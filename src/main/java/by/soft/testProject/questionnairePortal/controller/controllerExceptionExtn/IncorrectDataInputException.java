package by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.controller.ControllerException;

public class IncorrectDataInputException extends ControllerException {

    public IncorrectDataInputException() {
        super();
    }

    public IncorrectDataInputException(String message) {
        super(message);
    }

    public IncorrectDataInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataInputException(Throwable cause) {
        super(cause);
    }
}
