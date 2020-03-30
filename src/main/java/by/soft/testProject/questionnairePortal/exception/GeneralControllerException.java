package by.soft.testProject.questionnairePortal.exception;

public class GeneralControllerException extends RuntimeException {

    public GeneralControllerException() {
        super();
    }

    public GeneralControllerException(String message) {
        super(message);
    }

    public GeneralControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralControllerException(Throwable cause) {
        super(cause.getMessage());
    }
}
