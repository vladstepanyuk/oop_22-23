package minesweeper.exception.parser;

import minesweeper.utils.Command;

public class ArgsNumberException extends ParserException{
    public ArgsNumberException(Command.CommandIds id, int i){
        super(id.name() + " takes 2 arguments, got " + i);
    }
}
