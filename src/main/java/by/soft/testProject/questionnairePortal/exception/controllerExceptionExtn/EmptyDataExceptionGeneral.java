package by.soft.testProject.questionnairePortal.exception.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;

public class EmptyDataExceptionGeneral extends GeneralControllerException {

    public EmptyDataExceptionGeneral() {
        super();
    }

    public EmptyDataExceptionGeneral(String message) {
        super(message);
    }

    public EmptyDataExceptionGeneral(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDataExceptionGeneral(Throwable cause) {
        super(cause);
    }
}
