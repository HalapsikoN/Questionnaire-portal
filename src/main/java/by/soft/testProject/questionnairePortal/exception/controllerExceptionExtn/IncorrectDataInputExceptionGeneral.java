package by.soft.testProject.questionnairePortal.exception.controllerExceptionExtn;

import by.soft.testProject.questionnairePortal.exception.GeneralControllerException;

public class IncorrectDataInputExceptionGeneral extends GeneralControllerException {

    public IncorrectDataInputExceptionGeneral() {
        super();
    }

    public IncorrectDataInputExceptionGeneral(String message) {
        super(message);
    }

    public IncorrectDataInputExceptionGeneral(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataInputExceptionGeneral(Throwable cause) {
        super(cause);
    }
}
