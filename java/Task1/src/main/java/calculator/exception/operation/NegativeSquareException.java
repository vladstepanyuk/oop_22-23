package calculator.exception.operation;

public class NegativeSquareException extends  OperationException {
    public NegativeSquareException() {
        super("square of negative number");
    }
}
