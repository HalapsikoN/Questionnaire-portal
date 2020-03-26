package by.soft.testProject.questionnairePortal.controller.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.controller.ControllerException;

public class EmptyDataException extends ControllerException {

    public EmptyDataException() {
        super();
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDataException(Throwable cause) {
        super(cause);
    }
}
