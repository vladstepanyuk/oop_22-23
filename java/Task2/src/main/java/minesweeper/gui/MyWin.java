package minesweeper.gui;

import minesweeper.Minesweeper;

import java.awt.*;
import javax.swing.*;

/**
 * @author DarkRaha
 *
 */
public class MyWin extends JFrame {
    Context context;
    JPanel standardPanel;

    public JPanel getCurPanel() {
        return curPanel;
    }

    JPanel curPanel, prevPanel;
    Listener listener;
    public MyWin(Minesweeper game) {
        standardPanel = new JPanel(new BorderLayout());
        add(standardPanel);
        context = new Context(game, true);
        listener = new Listener(this, context);
        curPanel = new MyPanel(context, listener);
        standardPanel.add(curPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public  void openSettings() {
        new SettingsPanel(listener);

    }

    public  void goBack() {
        standardPanel.removeAll();
        curPanel = prevPanel;
        standardPanel.add(curPanel);
        standardPanel.updateUI();
    }

    public void showRecordTable(){
        new RecordPanel(context.getGame().getRecordsTable());
    }


}