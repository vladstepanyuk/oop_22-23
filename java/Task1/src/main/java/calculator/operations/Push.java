package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.operation.ArgsNumberException;
import calculator.exception.operation.OperationException;
import calculator.exception.operation.VariableException;
import calculator.utils.StringUtils;

public class Push implements Operation {
    private static final int VARIABLE_ARGC = 0;
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        if (args.length != 1) {
            throw new ArgsNumberException();
        } else if (StringUtils.isNumeric(args[VARIABLE_ARGC])) {
            context.push(Double.parseDouble(args[VARIABLE_ARGC]));
        } else if (context.containsVariable(args[VARIABLE_ARGC])) {
            context.push(context.getVariableValue(args[VARIABLE_ARGC]));
        } else {
            throw new VariableException(args[VARIABLE_ARGC]);
        }

    }
}
