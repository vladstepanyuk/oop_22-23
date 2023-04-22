package minesweeper.gui;

import javax.swing.*;

public class MyButton extends JButton {
    private int line;
    private int column;



    private boolean isFlag;

    public boolean isFlag() {
        return isFlag;
    }

    public int getLine() {
        return line;
    }


    public int getColumn() {
        return column;
    }


    public MyButton(int x, int y) {
        super();
        line = x;
        column = y;
        isFlag = false;
    }

    public void flag() {
        isFlag = true;
    }

    public void unFlag() {
        isFlag = false;
    }

}
