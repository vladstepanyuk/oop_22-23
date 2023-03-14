package calculator.exception.context;

public class PopException extends ContextException{
    public PopException() {
        super();
    }
    public PopException(String s) {
        super(s);
    }

    public PopException(String s, Throwable cause){
        super(s, cause);
    }
}
