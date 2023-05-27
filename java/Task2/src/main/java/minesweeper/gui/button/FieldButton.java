package minesweeper.gui.button;

import minesweeper.gui.Context;

import javax.swing.*;

public class FieldButton extends JButton {
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


    public FieldButton(int x, int y, Context context) {
        super();
        line = x;
        column = y;
        isFlag = false;
        addMouseListener(new FieldButtonMouseListener(context));
    }

    public void flag() {
        isFlag = true;
    }

    public void unFlag() {
        isFlag = false;
    }

}
