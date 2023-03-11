package calculator.operations;

import calculator.context.ProgramContext;
import calculator.exception.operation.ArgsFormatException;
import calculator.exception.operation.ArgsNumberException;
import calculator.exception.operation.OperationException;
import calculator.utils.StringUtils;

public class Define implements Operation{
    private static final int VARIABLE_ARGC = 0;
    private static final int VALUE_ARGC = 1;
    @Override
    public void exec(ProgramContext context, String[] args) throws OperationException {
        if (args.length != 2)
            throw new ArgsNumberException();
        else if (!StringUtils.isNumeric(args[VALUE_ARGC]))
            throw new ArgsFormatException(VALUE_ARGC + 1, Number.class);
        else if (StringUtils.isNumeric(args[VARIABLE_ARGC])) {
            throw new ArgsFormatException(VARIABLE_ARGC + 1, String.class);
        }

        context.putVariableValue(args[VARIABLE_ARGC], Double.parseDouble(args[VALUE_ARGC]));
    }
}
