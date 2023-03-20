package calculator.exception.factory;

import calculator.exception.CalculatorException;

public class FactoryException extends CalculatorException {
    public FactoryException(String s, Throwable cause) {
        super(s, cause);
    }

    public FactoryException(String s) {
        super(s);
    }

    public FactoryException() {
    }
}
