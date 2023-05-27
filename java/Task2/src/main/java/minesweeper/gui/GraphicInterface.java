package minesweeper.gui;

import minesweeper.Minesweeper;

public class GraphicInterface {
    private Minesweeper game;
    private MyWin Win;
    public GraphicInterface() {
        game = new Minesweeper();
        Win = new MyWin(game);
    }

    public void startGame(){
        Win.setVisible(true);
    }
}
