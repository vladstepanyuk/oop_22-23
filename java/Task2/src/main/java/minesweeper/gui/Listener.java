package minesweeper.gui;

import minesweeper.exception.MinesweeperException;
import minesweeper.utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static minesweeper.Field.*;

public class Listener implements ActionListener {
    MyWin win;
    Context context;

    int tmpLinesNumber;
    int tmpColumnsNumber;
    int tmpMinesNumber;
    String Name;


    boolean isClick = true;
    boolean needToRestart = false;

    public Listener(MyWin win, Context context1) {
        this.win = win;
        context = context1;
        tmpColumnsNumber = context1.getGame().getColumnsLength();
        tmpLinesNumber = context1.getGame().getLinesLength();
        tmpMinesNumber = context1.getGame().getMinesNumber();

    }


    private void click(int x, int y) throws MinesweeperException {
        int res = context.getGame().click(x, y);
        if (res == BOOM) needToRestart = true;
        else if (res == 0) {
            for (var xMove : xMoves)
                for (var yMove : yMoves)
                    if (context.getGame().correctXY(x + xMove, y + yMove) && !context.getGame().isOpen(x + xMove, y + yMove))
                        click(x + xMove, y + yMove);
        }
    }

    void MyButtonCase(ActionEvent e) throws MinesweeperException {
        int x = ((MyButton) e.getSource()).getLine();
        int y = ((MyButton) e.getSource()).getColumn();
        if (isClick) {
            click(x, y);
        } else {
            if (((MyButton) e.getSource()).isFlag()) {
                context.getGame().unFlag(x, y);
                ((MyButton) e.getSource()).unFlag();
            } else {

                if (context.getGame().flag(x, y)) {
                    if (WinPanel.winPanel(this, context.getGame().getTime()) == JOptionPane.OK_OPTION){
                        context.getGame().updateRecord(new Pair(Name, context.getGame().getTime()));
                    }
                    context.game.restartGame();
                    needToRestart = false;
                    ((MyPanel) win.getCurPanel()).updatePanel();
                }
                ((MyButton) e.getSource()).flag();
            }
        }
        ((MyPanel) win.getCurPanel()).updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().getClass().equals(MyButton.class)) {
                if (needToRestart) return;
                MyButtonCase(e);
            } else if (e.getSource().getClass().equals(JButton.class)) {
                if (((JButton) e.getSource()).getText().equals("flag")) isClick = !isClick;
                else if (((JButton) e.getSource()).getText().equals("restart")) {
                    context.game.restartGame();
                    needToRestart = false;
                    ((MyPanel) win.getCurPanel()).updatePanel();
                } else if (((JButton) e.getSource()).getText().equals("settings")) {
                    win.openSettings();
                } else if (((JButton) e.getSource()).getText().equals("record table")) {
                    win.showRecordTable();
                }

            } else if (e.getSource().getClass().equals(MyTextField.class)) {
                System.out.print(((MyTextField) e.getSource()).getText());
                switch (((MyTextField) e.getSource()).getType()) {
                    case LINES_LENGTH -> tmpLinesNumber = Integer.parseInt(((MyTextField) e.getSource()).getText());
                    case COLUMN_LENGTH -> tmpColumnsNumber = Integer.parseInt(((MyTextField) e.getSource()).getText());
                    case MINES_NUMBER -> tmpMinesNumber = Integer.parseInt(((MyTextField) e.getSource()).getText());
                    case NAME -> Name = ((MyTextField) e.getSource()).getText();
                }
            } else if (e.getSource().getClass().equals(ApplyButton.class)) {
                context.getGame().changeSettings(tmpLinesNumber, tmpColumnsNumber, tmpMinesNumber);
            }
        } catch (MinesweeperException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
}
