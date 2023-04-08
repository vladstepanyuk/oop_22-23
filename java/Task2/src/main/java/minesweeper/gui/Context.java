package minesweeper.gui;

import minesweeper.Minesweeper;

public class Context {
    public Minesweeper getGame() {
        return game;
    }

    Minesweeper game;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    boolean isClick;


    public Context(Minesweeper game, boolean isClick){
        this.game = game;
        this.isClick = isClick;
    }
}
