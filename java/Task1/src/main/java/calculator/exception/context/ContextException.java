package calculator.exception.context;

import calculator.exception.CalculatorException;

public class ContextException extends CalculatorException {
    public ContextException() {
        super();
    }
    public ContextException(String s) {
        super(s);
    }

    public ContextException(String s, Throwable cause){
        super(s, cause);
    }
}
