package calculator.exception.operation;

import calculator.operations.OperationIds;
import calculator.utils.OperationUtils;

public class ExecuteException extends OperationException {
    public ExecuteException(OperationIds id, Throwable cause) {
        super("unable to execute "+ OperationUtils.getNameById(id), cause);
    }
}
