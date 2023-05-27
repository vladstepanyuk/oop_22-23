package minesweeper.gui.button;

import minesweeper.exception.MinesweeperException;
import minesweeper.gui.Context;
import minesweeper.gui.GamePanel;
import minesweeper.gui.WinPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static minesweeper.Field.*;

public class FieldButtonMouseListener implements MouseListener {

    private final Context context;

    public FieldButtonMouseListener(Context context) {
        this.context = context;
    }

    private void click(int x, int y) throws MinesweeperException {
        int res = context.getGame().click(x, y);
        if (res == BOOM) context.setNeedToRestart(true);
        else if (res == 0) {
            for (var xMove : xMoves)
                for (var yMove : yMoves)
                    if (context.getGame().correctXY(x + xMove, y + yMove) && !context.getGame().isOpen(x + xMove, y + yMove))
                        click(x + xMove, y + yMove);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (context.isNeedToRestart()) return;
            int x = ((FieldButton) e.getSource()).getLine();
            int y = ((FieldButton) e.getSource()).getColumn();
            boolean isClick = context.isClick();
            if (e.getButton() == MouseEvent.BUTTON1) {
                click(x, y);
            } else {
                if (((FieldButton) e.getSource()).isFlag()) {
                    context.getGame().unFlag(x, y);
                    ((FieldButton) e.getSource()).unFlag();
                } else {
                    if (context.getGame().flag(x, y)) {
                        WinPanel.winPanel(context);
                        context.getGame().restartGame();
                        context.setNeedToRestart(false);
                        ((GamePanel) context.getWin().getCurPanel()).updatePanel();
                    }
                    ((FieldButton) e.getSource()).flag();
                }
            }
            ((GamePanel) context.getWin().getCurPanel()).updateView();
        } catch (MinesweeperException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
