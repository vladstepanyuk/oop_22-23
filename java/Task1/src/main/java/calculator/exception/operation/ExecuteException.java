package calculator.exception.operation;

import calculator.operations.OperationIds;

public class ExecuteException extends OperationException {
    public ExecuteException(OperationIds id, Throwable cause) {
        super("unable to execute "+ id.name(), cause);
    }
}
