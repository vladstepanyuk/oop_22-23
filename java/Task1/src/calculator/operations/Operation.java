package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.operation.OperationException;

public interface Operation {
    void exec(ProgramContext context, String[] args) throws OperationException;
}
