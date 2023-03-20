package calculator.exception.operation;

public class DivisionByZeroException extends OperationException {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
