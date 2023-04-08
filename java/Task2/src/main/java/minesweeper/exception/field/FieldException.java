package minesweeper.exception.field;

import minesweeper.exception.MinesweeperException;

public class FieldException extends MinesweeperException {
    public FieldException(String message, Throwable cause){
        super(message, cause);
    }
    public FieldException(String message){
        super(message);
    }
    public FieldException(){
        super();
    }
}
