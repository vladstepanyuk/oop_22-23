package minesweeper.gui;

import javax.swing.*;

public class ApplyButton extends JButton {
    public ApplyButton(Listener listener){
        super();
        setText("Apply");
        addActionListener(listener);
    }
}
