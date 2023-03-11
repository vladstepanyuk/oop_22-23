package calculator.utils;

import calculator.exception.parser.ArgsFormatException;
import calculator.exception.parser.ArgsNumberException;
import calculator.exception.parser.ParserException;
import calculator.operations.OperationIds;

public class CommandParser {
    private static final String SPACE = " ";

    public static Command pars(String str) throws ParserException {
        int index = str.indexOf(SPACE);
        if (index == -1){
            if (!OperationUtils.isOperationName(str)) throw new ArgsFormatException("no such operation: " + str);
            return new Command(OperationUtils.getIdByName(str), null);
        }
        String com = str.substring(0, index);
        if (!OperationUtils.isOperationName(com)) throw new ArgsFormatException("no such operation: " + com);
        if (com.equals( OperationUtils.getNameById(OperationIds.PUSH))) {
            String substring = str.substring(index + 1);
            if (substring.contains(SPACE)) throw new ArgsNumberException();
            String[] args = {substring};
            return new Command(OperationIds.PUSH, args);
        } else if (com.equals(OperationUtils.getNameById(OperationIds.DEFINE))) {
            int index2 = str.indexOf(SPACE, index + 1);
            String[] args = {str.substring(index + 1, index2), str.substring(index2 + 1)};
            if (args[1].contains(SPACE)) throw new ArgsNumberException();
            return new Command(OperationIds.DEFINE, args);
        }
        throw null;
    }
}
