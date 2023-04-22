package minesweeper.gui;

import minesweeper.Minesweeper;

public class Context {
    public Minesweeper getGame() {
        return game;
    }

    private Minesweeper game;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    private boolean isClick;


    public Context(Minesweeper game, boolean isClick){
        this.game = game;
        this.isClick = isClick;
    }
}
