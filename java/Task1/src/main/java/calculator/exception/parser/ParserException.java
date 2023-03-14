package calculator.exception.parser;

import calculator.exception.CalculatorException;

public class ParserException extends CalculatorException {

    public ParserException() {
        super();
    }
    public ParserException(String s) {
        super(s);
    }

    public ParserException(String s, Throwable cause){
        super(s, cause);
    }
}
