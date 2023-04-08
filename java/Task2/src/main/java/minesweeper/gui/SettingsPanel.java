package minesweeper.gui;

import javax.swing.*;

public class SettingsPanel{


    SettingsPanel(Listener listener) {

        JTextField linesField = new MyTextField(MyTextField.TextFieldType.LINES_LENGTH, listener);
        JTextField columnField = new MyTextField(MyTextField.TextFieldType.COLUMN_LENGTH, listener);
        JTextField minesField = new MyTextField(MyTextField.TextFieldType.MINES_NUMBER, listener);



        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Line Length:"));
        myPanel.add(linesField);

        myPanel.add(new JLabel("Column Length:"));
        myPanel.add(columnField);
        myPanel.add(new JLabel("Mines number:"));
        myPanel.add(minesField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Settings", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            new ApplyButton(listener).doClick();
        }
    }

}
