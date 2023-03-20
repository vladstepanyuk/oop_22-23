package calculator.utils;

import calculator.exception.operation.NoSuchOperationException;
import calculator.exception.parser.ArgsNumberException;
import calculator.exception.parser.NumberOutOfDoubleBound;
import calculator.exception.parser.ParserException;
import calculator.operations.OperationIds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandParser {
    private static final String SPACE = " ";
    public static final int MAX_SIZE = 308;
    static Logger logger = LogManager.getLogger(CommandParser.class.getName());

    public static Command pars(String str) throws ParserException {
        logger.info("parsing line");
        int index = str.indexOf(SPACE);
        String comName;
        OperationIds commId;
        String[] args = {};

        if (index == -1){
            comName = str;
        } else {
            comName = str.substring(0, index);
        }

        try {
            commId = OperationIds.valueOf(comName);
        } catch (IllegalArgumentException e){
            throw new ParserException("parser error", new NoSuchOperationException(comName));
        }

        if (comName.equals(OperationIds.PUSH.name())) {
            if (index == -1) throw new ArgsNumberException();
            String substring = str.substring(index + 1);
            if (substring.contains(SPACE)) throw new ArgsNumberException();
            args = new String[]{substring};
        } else if (comName.equals(OperationIds.DEFINE.name())) {
            if (index == -1) throw new ArgsNumberException();
            int index2 = str.indexOf(SPACE, index + 1);
            if (index2 == -1) throw new ArgsNumberException();
            args = new String[]{str.substring(index + 1, index2), str.substring(index2 + 1)};
            if (args[1].contains(SPACE)) throw new ArgsNumberException();
        }


        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > MAX_SIZE) throw new NumberOutOfDoubleBound(i+1);
        }

        return new Command(commId, args);
    }
}
