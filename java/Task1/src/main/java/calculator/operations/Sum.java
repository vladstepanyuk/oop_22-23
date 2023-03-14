package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;

public class Sum implements Operation {
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {

        try {
            double result = context.pop() + context.pop();
            context.push(result);
        } catch (ContextException e){
            throw new ExecuteException(OperationIds.SUM, e);
        }
    }
}
