package minesweeper.exception.game;

public class UsedAllFlagsException extends GameLogicException{
    public UsedAllFlagsException() {
        super("you used all your flags");
    }
}
