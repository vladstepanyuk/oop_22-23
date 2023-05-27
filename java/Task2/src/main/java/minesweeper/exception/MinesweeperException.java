package minesweeper.exception;

public class MinesweeperException extends Exception{
    public MinesweeperException(String message, Throwable cause){
        super(message, cause);
    }
    public MinesweeperException(String message){
        super(message);
    }
    public MinesweeperException(){
        super();
    }
}
