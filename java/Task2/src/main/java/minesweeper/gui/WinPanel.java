package minesweeper.gui;

import minesweeper.utils.Pair;

import javax.swing.*;
import java.awt.*;

public class WinPanel {
    static int winPanel(Listener listener, double time) {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0, 1));
        myPanel.add(new JLabel("YOU WIN. You time: " + time + "seconds!!!"));
        myPanel.add(new JLabel("Enter your name:"));
        myPanel.add(new MyTextField(MyTextField.TextFieldType.NAME, listener));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "RECORDS", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
}
