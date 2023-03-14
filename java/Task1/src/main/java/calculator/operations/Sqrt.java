package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.operation.ExecuteException;
import calculator.exception.operation.OperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.Math;


public class Sqrt implements Operation {
    static final Logger logger = LogManager.getLogger(Sqrt.class.getName());
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        logger.info("executing operation");

        try {
            double result =  Math.sqrt(context.pop());
            context.push(result);
        } catch (ContextException e){
            throw new ExecuteException(OperationIds.SQRT, e);
        }

    }
}
