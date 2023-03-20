package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.DivisionByZeroException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Division implements Operation {
    static final Logger logger = LogManager.getLogger(Division.class.getName());

    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        logger.info("executing operation");
        try {
            double firstNumber = context.pop();
            double secondNumber = context.pop();
            if (secondNumber == 0) throw new ExecuteException(OperationIds.DIVISION, new DivisionByZeroException());

            double result = firstNumber / secondNumber;
            context.push(result);
        } catch (ContextException e) {
            throw new ExecuteException(OperationIds.DIVISION, e);
        }
    }
}
