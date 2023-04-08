package minesweeper.exception.field;


import minesweeper.config.Configuration;

public class IndexesOutOfBoundException extends FieldException {

    public IndexesOutOfBoundException(int i, int j, Configuration configuration){
        super("("+i+", "+j+") out of bound. Field size is "+ configuration.getLineLength() + "x "+configuration.getColumnLength());
    }
}
