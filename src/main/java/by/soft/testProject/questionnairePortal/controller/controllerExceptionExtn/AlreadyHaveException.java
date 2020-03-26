package by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.controller.ControllerException;

public class AlreadyHaveException extends ControllerException {

    public AlreadyHaveException() {
        super();
    }

    public AlreadyHaveException(String message) {
        super(message);
    }

    public AlreadyHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyHaveException(Throwable cause) {
        super(cause);
    }
}
