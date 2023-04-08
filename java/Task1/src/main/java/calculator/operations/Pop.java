package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pop implements Operation{
    static final Logger logger = LogManager.getLogger(Pop.class.getName() );
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        logger.info("executing operation");
        try {
            context.pop();
        } catch (ContextException e) {
            throw new ExecuteException(OperationIds.POP, e);
        }

    }
}
