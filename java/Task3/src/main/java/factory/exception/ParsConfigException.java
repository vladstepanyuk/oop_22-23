package factory.exception;

public class ParsConfigException extends Exception{
    public ParsConfigException() {
    }

    public ParsConfigException(String message) {
        super(message);
    }

    public ParsConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}