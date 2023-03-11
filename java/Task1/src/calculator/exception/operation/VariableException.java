package calculator.exception.operation;

public class VariableException extends OperationException{
    public VariableException(String variableName){
        super("there's no such variable: "+ variableName);
    }
}
