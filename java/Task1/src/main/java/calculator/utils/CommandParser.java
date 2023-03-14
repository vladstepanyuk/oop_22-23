package calculator.utils;

import calculator.exception.operation.NoSuchOperationException;
import calculator.exception.parser.ArgsNumberException;
import calculator.exception.parser.ParserException;
import calculator.operations.OperationIds;

public class CommandParser {
    private static final String SPACE = " ";

    public static Command pars(String str) throws ParserException {
        int index = str.indexOf(SPACE);
        String comName;
        OperationIds commId;

        if (index == -1){
            comName = str;
        } else {
            comName = str.substring(0, index);
        }

        try {
            commId = OperationIds.getIdByName(comName);
        } catch (NoSuchOperationException e) {
            throw new ParserException("parser error", e);
        }

        if (comName.equals(OperationIds.PUSH.getName())) {
            if (index == -1) throw new ArgsNumberException();
            String substring = str.substring(index + 1);
            if (substring.contains(SPACE)) throw new ArgsNumberException();
            String[] args = {substring};
            return new Command(OperationIds.PUSH, args);
        } else if (comName.equals(OperationIds.DEFINE.getName())) {
            if (index == -1) throw new ArgsNumberException();
            int index2 = str.indexOf(SPACE, index + 1);
            if (index2 == -1) throw new ArgsNumberException();
            String[] args = {str.substring(index + 1, index2), str.substring(index2 + 1)};
            if (args[1].contains(SPACE)) throw new ArgsNumberException();
            return new Command(OperationIds.DEFINE, args);
        } else {
            return new Command(commId, null);
        }
    }
}
