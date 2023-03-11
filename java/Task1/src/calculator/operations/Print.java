package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;

public class Print implements Operation {
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        try {
            System.out.println(context.peek());
        } catch (ContextException e) {
            throw new ExecuteException(OperationIds.PRINT, e);
        }

    }
}
