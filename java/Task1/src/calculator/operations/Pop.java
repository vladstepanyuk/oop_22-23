package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;

public class Pop implements Operation{
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        try {
            context.pop();
        } catch (ContextException e) {
            throw new ExecuteException(OperationIds.POP, e);
        }

    }
}
