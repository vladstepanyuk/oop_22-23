package minesweeper.utils;


import minesweeper.exception.parser.ArgsNumberException;
import minesweeper.exception.parser.ParserException;
import minesweeper.exception.parser.UnknownCommandException;

public class CommandParser {

    public static final String SPACE = " ";


    public static Command parsCommandLine(String str) throws ParserException {
        String[] strSplit = str.split(SPACE);

        Command.CommandIds id = Command.CommandIds.valueOf(strSplit[0]);

        if (id.getArgsNum() != strSplit.length - 1) throw new ArgsNumberException(id, strSplit.length - 1);


        int[] args = new int[strSplit.length - 1];
        for (int i = 1; i < strSplit.length; i++) {
            args[i - 1] = Integer.parseInt(strSplit[i]);
        }
        return new Command(id, args);
    }
}
