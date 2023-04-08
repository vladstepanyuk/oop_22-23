package minesweeper.gui;

import javax.swing.*;

public class MyTextField extends JTextField {
    private static final int FIELD_WIDTH = 10;


    TextFieldType type;
    public enum TextFieldType {
        LINES_LENGTH,
        COLUMN_LENGTH,
        MINES_NUMBER,
        NAME
    }
    public TextFieldType getType() {
        return type;
    }
    public MyTextField(TextFieldType t, Listener listener) {
        super(FIELD_WIDTH);
        type = t;
        addActionListener(listener);
    }

}
