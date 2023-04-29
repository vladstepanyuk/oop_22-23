package minesweeper.gui;

import minesweeper.Minesweeper;

public class Context {

    private final MyWin win;


    private final Minesweeper game;
    private boolean isClick = true;

    private boolean needToRestart = false;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }


    public Minesweeper getGame() {
        return game;
    }


    public Context(Minesweeper game, MyWin win){
        this.game = game;
        this.win = win;
    }

    public boolean isNeedToRestart() {
        return needToRestart;
    }

    public void setNeedToRestart(boolean needToRestart) {
        this.needToRestart = needToRestart;
    }

    public MyWin getWin() {
        return win;
    }
}
