package minesweeper.gui;

import minesweeper.utils.Pair;

import javax.swing.*;
import java.awt.*;

public class WinPanel {

    private static final int FIELD_WIDTH = 10;
    public static int winPanel(Context context) {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0, 1));
        myPanel.add(new JLabel("YOU WIN. You time: " + String.format("%.3f", context.getGame().getTime()) + " seconds!!!"));
        myPanel.add(new JLabel("Enter your name:"));
        JTextField field = new JTextField(FIELD_WIDTH);
        myPanel.add(field);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "RECORDS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            context.getGame().updateRecord(new Pair(field.getText(), context.getGame().getTime()));
        }
        return result;
    }
}
