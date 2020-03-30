package by.soft.testProject.questionnairePortal.exception.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;

public class AlreadyHaveExceptionGeneral extends GeneralControllerException {

    public AlreadyHaveExceptionGeneral() {
        super();
    }

    public AlreadyHaveExceptionGeneral(String message) {
        super(message);
    }

    public AlreadyHaveExceptionGeneral(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyHaveExceptionGeneral(Throwable cause) {
        super(cause);
    }
}
