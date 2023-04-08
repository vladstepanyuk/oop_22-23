package calculator.exception.parser;

public class ArgsFormatException extends ParserException {
    public ArgsFormatException() {
        super();
    }
    public ArgsFormatException(String s) {
        super(s);
    }

    public ArgsFormatException(String s, Throwable cause){
        super(s, cause);
    }
}
