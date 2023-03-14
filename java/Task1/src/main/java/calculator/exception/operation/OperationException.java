package calculator.exception.operation;

import calculator.exception.CalculatorException;

public class OperationException extends CalculatorException {
    public OperationException() {
        super();
    }

    public OperationException(String s) {
        super(s);
    }

    public OperationException(String s, Throwable cause) {
        super(s, cause);
    }
}
