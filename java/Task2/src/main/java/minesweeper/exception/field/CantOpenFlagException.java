package minesweeper.exception.field;

public class CantOpenFlagException extends FieldException{
    public CantOpenFlagException(int i, int j){
        super("("+i+", "+j+") is flagged. You can't open it");
    }

}
