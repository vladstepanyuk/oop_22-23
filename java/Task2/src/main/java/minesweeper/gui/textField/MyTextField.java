package minesweeper.gui.textField;


import javax.swing.*;

public class MyTextField extends JTextField {
    private static final int FIELD_WIDTH = 10;


    private TextFieldType type;

    public void setType(TextFieldType type) {
        this.type = type;
    }

    public enum TextFieldType {
        LINES_LENGTH,
        COLUMN_LENGTH,
        MINES_NUMBER,
    }
    public TextFieldType getType() {
        return type;
    }
    public MyTextField(TextFieldType t) {
        super(FIELD_WIDTH);
        setType(t);
    }

}
