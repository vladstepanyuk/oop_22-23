package calculator.exception.operation;

public class NoSuchOperationException extends OperationException {
    public NoSuchOperationException(String opName){
        super("Operation " + opName + " doesn't exist");
    }
}
