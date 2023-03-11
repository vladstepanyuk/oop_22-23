package calculator.exception;

public class CalculatorException extends Exception{
    public CalculatorException() {
        super();
    }
    public CalculatorException(String s) {
        super(s);
    }

    public CalculatorException(String s, Throwable cause){
        super(s, cause);
    }
}
