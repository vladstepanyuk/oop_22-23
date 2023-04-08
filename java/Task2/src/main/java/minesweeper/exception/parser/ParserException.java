package minesweeper.exception.parser;

import minesweeper.exception.MinesweeperException;

public class ParserException extends MinesweeperException {
    public ParserException(String message, Throwable cause){
        super(message, cause);
    }
    public ParserException(String message){
        super(message);
    }
    public ParserException(){
        super();
    }
}
