package calculator.exception.operation;

public class ArgsFormatException extends OperationException{
    public ArgsFormatException(int argc, Class<?> clas){
        super(argc + " argument has wrong class. must be "+ clas.getName());
    }
}
